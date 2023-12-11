package ua.lpnu.security_system_simulator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lpnu.security_system_simulator.model.building.BuildingLevel;
import ua.lpnu.security_system_simulator.model.event.EventType;
import ua.lpnu.security_system_simulator.model.stats.EventStatistics;
import ua.lpnu.security_system_simulator.repository.BuildingRepository;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
//@RequestMapping("stats")
public class StatsController {
    BuildingRepository repository;
    EventStatistics statistics;

    @Autowired
    public StatsController(BuildingRepository repository, EventStatistics statistics){
        this.repository = repository;
        this.statistics = statistics;
    }
    @GetMapping("/stats/eventTypePerBuilding/{id}")
    public ResponseEntity<Map<EventType, Long>> eventTypePerBuilding(@PathVariable("id") String id){
        try {
            return new ResponseEntity<>(statistics.eventTypePerBuilding(id), HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
