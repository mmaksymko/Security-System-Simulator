
package ua.lpnu.security_system_simulator.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lpnu.security_system_simulator.model.building.BuildingLevel;
import ua.lpnu.security_system_simulator.model.system.LogManager;
import ua.lpnu.security_system_simulator.model.event.Event;
import ua.lpnu.security_system_simulator.repository.BuildingRepository;

import java.util.List;

@RestController
public class LogController {
    private final BuildingRepository repository;
    private final LogManager logManager;

    @Autowired
    public LogController(BuildingRepository repository, LogManager logManager){
        this.repository = repository;
        this.logManager = logManager;
    }

    @GetMapping("buildings/{id}/logs")
    public ResponseEntity<List<List<Event>>> getLogs(@PathVariable("id") String id){
        try {
            var result = repository.findById(id);
            return result.isPresent()
                    ? new ResponseEntity<>(logManager.getAllLogs(result.get()), HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("buildings/{id}/logs/{index}")
    public ResponseEntity<List<Event>> getLog(@PathVariable("id") String id, @PathVariable("index") int index){
        try {
            var result = repository.findById(id);
            return result.isPresent()
                    ? new ResponseEntity<>(logManager.getLog(result.get(), index), HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("buildings/{id}/logs/")
    public ResponseEntity<BuildingLevel> rollbackToLog(@PathVariable("id") String id, @RequestBody Integer index) {
        try {
            var optionalBuilding = repository.findById(id);
            if (optionalBuilding.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            var building = logManager.rollbackToIndex(optionalBuilding.get(), index);
            var result = repository.save(building);
            repository.deleteById(building.getId());
            repository.save(building);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
   }

}