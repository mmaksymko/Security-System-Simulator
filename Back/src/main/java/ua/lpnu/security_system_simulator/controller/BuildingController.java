package ua.lpnu.security_system_simulator.controller;

import netscape.javascript.JSObject;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lpnu.security_system_simulator.model.building.BuildingLevel;
import ua.lpnu.security_system_simulator.model.building.builder.building.ApartmentBuildingBuilder;
import ua.lpnu.security_system_simulator.model.building.builder.building.OfficeBuildingBuilder;
import ua.lpnu.security_system_simulator.repository.BuildingRepository;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
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
            if (!validateBuilding(buildingLevel)){
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }

            repository.save(buildingLevel);
            return new ResponseEntity<>(buildingLevel, HttpStatus.OK);
        } catch (DuplicateKeyException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/buildings/{id}")
    public ResponseEntity<BuildingLevel> updateBuilding(@PathVariable("id") String id, @RequestBody BuildingLevel buildingLevel) {
        try {
            if (!validateBuilding(buildingLevel)){
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }

            var result = repository.findById(id);
            if (result.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            buildingLevel.setId(id);
            repository.save(buildingLevel);
            return new ResponseEntity<>(buildingLevel, HttpStatus.OK);
        } catch (DuplicateKeyException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
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
    @PostMapping("/buildings/residential")
    public  ResponseEntity<BuildingLevel> generateApartmentBuilding(HttpEntity<String> httpEntity){
        try {
            JSONObject json = new JSONObject(httpEntity.getBody());
            ApartmentBuildingBuilder builder = new ApartmentBuildingBuilder();
            builder.seNumberOfFloors(Integer.parseInt(json.get("floors").toString()));
            builder.setNumberOfRoomsPerFloor(Integer.parseInt(json.get("rooms").toString()));
            BuildingLevel result = builder.build();
            repository.save(result)
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/buildings/office")
    public  ResponseEntity<BuildingLevel> generateOfficeBuilding(HttpEntity<String> httpEntity){
        try {
            JSONObject json = new JSONObject(httpEntity.getBody());
            OfficeBuildingBuilder builder = new OfficeBuildingBuilder();
            builder.seNumberOfFloors(Integer.parseInt(json.get("floors").toString()));
            builder.setNumberOfRoomsPerFloor(Integer.parseInt(json.get("rooms").toString()));
            BuildingLevel result = builder.build();
            repository.save(result);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean validateBuilding(BuildingLevel building) {
        if (building.getNumberOfComponents() >= 200){
            return false;
        }
        for(var component : building.depthFirstSearch()){
            if (component.getNumberOfComponents()>=20) {
                return false;
            }
        }
        return true;
    }
}
