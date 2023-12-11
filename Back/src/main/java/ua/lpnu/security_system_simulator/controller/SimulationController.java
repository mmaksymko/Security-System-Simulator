//package ua.lpnu.security_system_simulator.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
//import ua.lpnu.security_system_simulator.model.building.BuildingLevel;
//import ua.lpnu.security_system_simulator.model.building.Room;
//import ua.lpnu.security_system_simulator.model.event.DangerLevel;
//import ua.lpnu.security_system_simulator.model.event.Event;
//import ua.lpnu.security_system_simulator.model.event.EventFactory;
//import ua.lpnu.security_system_simulator.model.event.EventType;
//import ua.lpnu.security_system_simulator.repository.BuildingRepository;
//
//import java.util.List;
//import java.util.Random;
//import java.util.stream.StreamSupport;
//
//@CrossOrigin(origins = "http://127.0.0.1:5500/", methods = {RequestMethod.GET, RequestMethod.POST})
//@RestController
//public class SSEController {
//    private final CustomTaskExecutor taskExecutor;
//    private BuildingRepository repo;
//    private SseEmitter emitter;
//    private boolean isPaused;
//
//    @Autowired
//    public SSEController(CustomTaskExecutor taskExecutor, BuildingRepository repo) {
//        this.taskExecutor = taskExecutor;
//        this.repo = repo;
//        emitter = new SseEmitter(-1L);
//        isPaused = false;
//    }
//
//    @GetMapping("/stream-sse-multi")
//    public SseEmitter streamSseMulti() {
//        Random random = new Random();
//        emitter = new SseEmitter();
//      //  isPaused = false;
//
//        EventFactory eventFactory = new EventFactory();
//
//        BuildingLevel building = repo.findAll().get(0);
//
//        List<Room> rooms = StreamSupport.stream(building.breadthFirstSearch().spliterator(), false)
//                .filter(component -> component instanceof Room).map(component -> (Room) component).toList();
//
//        taskExecutor.taskExecutor().execute(() -> {
//            try {
//                while (true){
//                    if (!isPaused) {
//                        Random r = new Random();
//                        int randomInt = r.nextInt(10) + 1;
//                        Thread.sleep(random.nextInt(0, 500));
//                        emitter.send(SseEmitter.event().name("Update").data(eventFactory.createEvent(
//                                EventType.values()[random.nextInt(0, EventType.values().length)],
//                                rooms.get(random.nextInt(0, rooms.size())),
//                                DangerLevel.values()[random.nextInt(0, DangerLevel.values().length)])));
//                    }
//                }
////                emitter.complete();
//            } catch (Exception ex) {
//                emitter.completeWithError(ex);
//            }
//        });
//
//        return emitter;
//    }
//
//    @GetMapping("/stream-sse-multi/stop")
//    public void stop() {
//        taskExecutor.taskExecutor().shutdownNow();
//        emitter.complete();
//    }
//
//        @GetMapping("/stream-sse-multi/pause")
//    public void pause() {
//        isPaused = true;
////        taskExecutor.taskExecutor().shutdownNow();
//        //      emitter.complete();
//    }
//
//    @GetMapping("/stream-sse-multi/resume")
//    public void resume() {
//        isPaused = false;
//    }
//}




package ua.lpnu.security_system_simulator.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Random;
import java.util.stream.StreamSupport;

//@CrossOrigin(origins = "http://127.0.0.1:5500/", methods = {RequestMethod.GET, RequestMethod.POST})
@CrossOrigin
@RestController
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
//        this.building = building;
    }

    @CrossOrigin
    @GetMapping("/simulation/{id}")
    public Flux<Event> streamSseMulti(@PathVariable("id") String id) {
       try {
           return Mono.fromCallable(() -> repo.findById(id)).subscribeOn(Schedulers.boundedElastic()).flatMapMany(optionalBuilding -> {
               if (optionalBuilding.isEmpty()) {
                   return Flux.empty();
               }
               isPaused = false;
               building = optionalBuilding.get();
               logManager.createNewLog(building);
               try {
                   logManager.startLog(building);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
               var rooms = StreamSupport.stream(building.breadthFirstSearch().spliterator(), false).filter(component -> component instanceof Room).map(component -> (Room) component)
                       .toList();
               return Flux.interval(Duration.ofMillis(random.nextInt(0, 250)))
                       .parallel(4)
                       .runOn(Schedulers.parallel()).flatMap(tick -> {
                           try {
                               var event = eventFactory.createEvent(EventType.values()[random.nextInt(1, 6)],
                                       rooms.get(random.nextInt(0, rooms.size())),
                                       DangerLevel.values()[random.nextInt(1, DangerLevel.values().length)]);
                               var triggeredEvent = event.start();
                               var result = triggeredEvent == null ? Flux.just(event) : Flux.just(event, triggeredEvent).parallel(2);
                               return isPaused ? Flux.empty() : result;
                           } catch (InterruptedException e) {
                               return Flux.error(new RuntimeException(e));
                           }
                       })
                       .sequential()
                       .takeUntil(event -> isStopped);
//                       .unti;
//                       .doOnCancel(this::stop)
//                       .doOnTerminate(this::stop);
           });
//       } catch (RuntimeException e){
//
       } catch (Exception e) {
            return Flux.empty();
       }
    }


//    @GetMapping("/simulation/stop")
//    public void stop() {
//        if (building != null){
//            building = repo.findById(building.getId()).get();
//            repo.save(building);
//            logManager.createNewLog(building);
//            building = null;
//        }
//        isStopped = true;
//    }

    @GetMapping("/simulation/pause")
    public void pause() {
        isPaused = true;
    }

    @GetMapping("/simulation/resume")
    public void resume() {
        isPaused = false;
    }

    @GetMapping("/simulation/save")
    public void save() {
        if (logManager!=null){
            logManager.createNewLog(building);
            repo.save(building);
        }
    }

}

