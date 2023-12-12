package ua.lpnu.security_system_simulator.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import ua.lpnu.security_system_simulator.model.building.BuildingLevel;
import ua.lpnu.security_system_simulator.model.building.Room;
import ua.lpnu.security_system_simulator.model.system.LogManager;
import ua.lpnu.security_system_simulator.model.event.DangerLevel;
import ua.lpnu.security_system_simulator.model.event.Event;
import ua.lpnu.security_system_simulator.model.event.EventFactory;
import ua.lpnu.security_system_simulator.model.event.EventType;
import ua.lpnu.security_system_simulator.repository.BuildingRepository;

import java.time.Duration;
import java.util.Objects;
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
    private boolean isPaused;
    private boolean isStopped;
    private BuildingLevel building;

    @Autowired
    public SimulationController(BuildingRepository repo, EventFactory eventFactory, LogManager logManager) {
        this.repo = repo;
        this.eventFactory = eventFactory;
        this.logManager = logManager;
        this.random = new Random();
        this.isPaused = false;
        this.isStopped = false;
    }

    @Operation(description = "Start simulation of security system.", responses = {@ApiResponse(description = "Success"
            , responseCode = "200"), @ApiResponse(description = "Bad request", responseCode = "400")})
    @CrossOrigin
    @GetMapping("/simulation/{id}")
    public Flux<Event> streamSseMulti(@PathVariable("id") String id) {
        try {
            return Mono.fromCallable(() -> repo.findById(id)).subscribeOn(Schedulers.boundedElastic())
                    .flatMapMany(optionalBuilding -> {
                        if (optionalBuilding.isEmpty()) {return Flux.empty();}
                        isPaused = false;
                        building = optionalBuilding.get();
                        logManager.createNewLog(building);
                        try {logManager.startLog(building);} catch (InterruptedException e) {
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
                                var result = triggeredEvent == null ? Flux.just(event) : Flux.just(event,
                                        triggeredEvent).parallel(2);
                                return isPaused ? Flux.empty() : result;
                            } catch (InterruptedException e) {return Flux.error(new RuntimeException(e));}
                        }).sequential().takeUntil(event -> isStopped);
                    });
        } catch (Exception e) {return Flux.empty();}
    }

    @Operation(description = "Stops simulation of security system", responses = {@ApiResponse(description = "Success"
            , responseCode = "200"), @ApiResponse(description = "Not Found", responseCode = "404")})
    @GetMapping("/simulation/stop")
    public ResponseEntity<Void> stop() {
        if (building != null) {
            building = repo.findById(building.getId()).get();
            repo.save(building);
            logManager.createNewLog(building);
            building = null;
            return new ResponseEntity<>(HttpStatus.OK);
        }
        isStopped = true;
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(description = "Pauses paused simulation of security system.", responses = {@ApiResponse(description =
            "Success", responseCode = "200")})
    @GetMapping("/simulation/pause")
    public ResponseEntity<Object> pause() {
        isPaused = true;
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(description = "Continues paused simulation of security system.", responses =
            {@ApiResponse(description = "Success", responseCode = "200")})
    @GetMapping("/simulation/resume")
    public ResponseEntity<Object> resume() {
        isPaused = false;
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(description = "Saves simulation of security system to the database.", responses =
            {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Not Found",
                    responseCode = "404")})
    @GetMapping("/simulation/save")
    public ResponseEntity<Object> save() {
        if (logManager != null) {
            logManager.createNewLog(building);
            repo.save(building);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}