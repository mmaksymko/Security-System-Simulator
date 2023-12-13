package ua.lpnu.security_system_simulator.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import ua.lpnu.security_system_simulator.model.building.BuildingLevel;
import ua.lpnu.security_system_simulator.model.building.Room;
import ua.lpnu.security_system_simulator.service.LogManager;
import ua.lpnu.security_system_simulator.model.event.DangerLevel;
import ua.lpnu.security_system_simulator.model.event.Event;
import ua.lpnu.security_system_simulator.model.event.EventFactory;
import ua.lpnu.security_system_simulator.model.event.EventType;
import ua.lpnu.security_system_simulator.repository.BuildingRepository;

import java.time.Duration;
import java.util.Random;
import java.util.stream.StreamSupport;

@CrossOrigin
@RestController
@Tag(name = "Simulation")
public class SimulationController {
    private final BuildingRepository repo;
    private final EventFactory eventFactory;
    private final LogManager logManager;
    private final Random random;

    @Autowired
    public SimulationController(BuildingRepository repo, EventFactory eventFactory, LogManager logManager) {
        this.repo = repo;
        this.eventFactory = eventFactory;
        this.logManager = logManager;
        this.random = new Random();
    }

    @Operation(description = "Start simulation of security system.", responses = {@ApiResponse(description = "Success"
            , responseCode = "200"), @ApiResponse(description = "Bad request", responseCode = "400")})
    @CrossOrigin
    @GetMapping("/simulation/{id}")
    public Flux<Event> streamSseMulti(@PathVariable("id") String id) {
        try {
            return Mono.fromCallable(() -> repo.findById(id)).subscribeOn(Schedulers.boundedElastic())
                    .flatMapMany(optionalBuilding -> {
                        if (optionalBuilding.isEmpty()) {
                            return Flux.empty();
                        }

                        BuildingLevel building = optionalBuilding.get();
                        logManager.createNewLog(building);

                        try {
                            logManager.startLog(building);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        var rooms = StreamSupport.stream(building.breadthFirstSearch().spliterator(), false)
                                .filter(component -> component instanceof Room).map(component -> (Room) component)
                                .toList();
                        return Flux.interval(Duration.ofMillis(random.nextInt(0, 250)))
                                .onBackpressureDrop().parallel(4)
                                .runOn
                        (Schedulers.parallel()).flatMap(tick -> {
                            try {
                                var event = eventFactory.createEvent(EventType.values()[random.nextInt(1, 6)],
                                        rooms.get(random.nextInt(0, rooms.size())),
                                        DangerLevel.values()[random.nextInt(1, DangerLevel.values().length)]);
                                var triggeredEvent = event.start();
                                return triggeredEvent == null ? Flux.just(event) : Flux.just(event,
                                        triggeredEvent).parallel(2);
                            } catch (InterruptedException e) {return Flux.error(new RuntimeException(e));}
                        }).sequential();
                    });
        } catch (Exception e) {return Flux.empty();}
    }

    @Operation(description = "Saves simulation of security system to the database.", responses =
            {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Not Found",
                    responseCode = "404")})
    @PostMapping("/simulation/{id}/save")
    public ResponseEntity<BuildingLevel> save(@PathVariable("id") String id, @RequestBody String events) {
        try {
            var optional =repo.findById(id);
            if (optional.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            var result = logManager.saveLog(optional.get(), new JSONArray(events));
            repo.save(result);
            return new ResponseEntity<>(result,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
}