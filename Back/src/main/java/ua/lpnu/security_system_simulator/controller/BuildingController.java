package ua.lpnu.security_system_simulator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lpnu.security_system_simulator.model.building.BuildingLevel;
import ua.lpnu.security_system_simulator.repository.BuildingRepository;

import java.util.List;

@RestController
public class BuildingController {
    BuildingRepository repository;

    @Autowired
    public BuildingController(BuildingRepository repository){
        this.repository = repository;
    }

    @GetMapping("/buildings")
    public ResponseEntity<List<BuildingLevel>> getAllBuildings(){
        try {
            return repository.count()==0
                    ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                    : new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/buildings/{id}")
    public ResponseEntity<BuildingLevel> getBuilding(@PathVariable("id") String id) {
        try {
            var result = repository.findById(id);
            return result.isPresent()
                    ? new ResponseEntity<>(result.get(), HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/buildings")
    public ResponseEntity<BuildingLevel> createBuilding(@RequestBody BuildingLevel buildingLevel) {
        try {
            repository.save(buildingLevel);
            return new ResponseEntity<>(buildingLevel, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/buildings/{id}")
    public ResponseEntity<BuildingLevel> updateBuilding(@PathVariable("id") String id, @RequestBody BuildingLevel buildingLevel) {
        try {
            var result = repository.findById(id);
            if (result.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            buildingLevel.setId(id);
            repository.save(buildingLevel);
            return new ResponseEntity<>(buildingLevel, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/buildings/{id}")
    public ResponseEntity<BuildingLevel> deleteBuilding(@PathVariable("id") String id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}