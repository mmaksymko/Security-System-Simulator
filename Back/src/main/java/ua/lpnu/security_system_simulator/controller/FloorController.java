package ua.lpnu.security_system_simulator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import ua.lpnu.security_system_simulator.model.building.BuildingComponent;
import ua.lpnu.security_system_simulator.model.building.BuildingLevel;
import ua.lpnu.security_system_simulator.repository.BuildingRepository;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.StreamSupport;

@RestController
public class FloorController {
    BuildingRepository repository;

    @Autowired
    public FloorController(BuildingRepository repository){
        this.repository = repository;
    }

    @GetMapping("/buildings/{id}/floors")
    public ResponseEntity<List<BuildingComponent>> getAllFloors(@PathVariable("id") String id){
        try {
            var result = repository.findById(id);
            return result.isPresent()
                      ? new ResponseEntity<>(result.get().getComponents(), HttpStatus.OK)
                      : new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/floors/{floorId}")
    public ResponseEntity<BuildingLevel> getFloor(@PathVariable("floorId") String floorId) {
        try {
            var optionalBuildingLevel = repository
                    .findAll()
                    .stream()
                    .flatMap(building -> StreamSupport.stream(building.breadthFirstSearch().spliterator(), false))
                    .filter(floor -> floor.getId().equals(floorId))
                    .findFirst();
            return optionalBuildingLevel.isPresent()
                    ? new ResponseEntity<>((BuildingLevel) optionalBuildingLevel.get(), HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/buildings/{id}/floors/{floorId}")
    public ResponseEntity<BuildingLevel> getFloor(@PathVariable("id") String id, @PathVariable("floorId") String floorId){
        try {
            var building = repository.findById(id);
            if (building.isPresent()) {
                var level = building.get().getComponents().stream().filter(floor -> floor.getId().equals(floorId)).findFirst();
                if (level.isPresent()){
                    return new ResponseEntity<>((BuildingLevel) level.get(), HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/buildings/{id}/")
    public ResponseEntity<BuildingLevel> addFloor(@RequestBody BuildingLevel buildingLevel, @PathVariable("id") String id) {
        try {
            if(!validateFloor(buildingLevel)){
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }

            var optionalBuilding = repository.findById(id);
            if(optionalBuilding.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            var building = optionalBuilding.get();
            if (building.getNumberOfComponents() >= 200){
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }

            building.getComponents().add(buildingLevel);
            repository.save(building);
            return new ResponseEntity<>(building, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("floors/{floorId}")
    public ResponseEntity<BuildingLevel> updateFloor(@PathVariable("floorId") String floorId, @RequestBody BuildingLevel buildingLevel) {
        try {
            if (!validateFloor(buildingLevel)) {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }

            for (var building : repository.findAll()){
                for (var floor : building.breadthFirstSearch()){
                    if (floor.getId().equals(floorId)){
                        return updateFloor(building, floor, buildingLevel);
                    }
                }
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("buildings/{buildingId}/floors/{floorId}")
    public ResponseEntity<BuildingLevel> updateFloor(@PathVariable("buildingId") String buildingId, @PathVariable("floorId") String floorId, @RequestBody BuildingLevel buildingLevel) {
        try {
            if(!validateFloor(buildingLevel)){
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }

            var building = repository.findById(buildingId).orElse(null);
            if (building == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            var result = building
                            .getComponents()
                            .stream()
                            .filter(level -> level.getId().equals(floorId))
                            .findFirst()
                            .orElse(null);
            return updateFloor(building, result, buildingLevel);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("floors/{floorId}")
    public ResponseEntity<BuildingLevel> deleteFloor(@PathVariable("floorId") String floorId) {
        try {
            for (var building : repository.findAll()){
                for (var floor : building.breadthFirstSearch()){
                    if (floor.getId().equals(floorId)){
                        return deleteFloor(building, floor);
                    }
                }
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("buildings/{buildingId}/floors/{floorId}")
    public ResponseEntity<BuildingLevel> updateFloor(@PathVariable("buildingId") String buildingId, @PathVariable("floorId") String floorId){
        try {
            var building = repository.findById(buildingId);
            if (building.isPresent()) {
                var floor = building.get().getComponents().stream().filter(level -> level.getId().equals(floorId)).findFirst();
                if (floor.isPresent()) {
                    return deleteFloor(building.get(), floor.get());
                }
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<BuildingLevel> updateFloor(BuildingLevel building, BuildingComponent toReplace, BuildingLevel replaceWith){
        try {
            if (toReplace == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            var floor = (BuildingLevel) toReplace;
            floor.setName(replaceWith.getName());
            floor.setComponents(replaceWith.getComponents());
            repository.save(building);
            return new ResponseEntity<>(floor, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<BuildingLevel> deleteFloor(BuildingLevel building, BuildingComponent floor){
        try {
            building.removeComponent(floor);
            repository.save(building);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean validateFloor(BuildingLevel floor) {
        return floor != null && floor.getNumberOfComponents() <= 20;
    }
}
