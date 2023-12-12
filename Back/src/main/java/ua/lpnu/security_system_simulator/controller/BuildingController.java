package ua.lpnu.security_system_simulator.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lpnu.security_system_simulator.model.building.BuildingLevel;
import ua.lpnu.security_system_simulator.model.building.builder.building.ApartmentBuildingBuilder;
import ua.lpnu.security_system_simulator.model.building.builder.building.BuildingBuilder;
import ua.lpnu.security_system_simulator.model.building.builder.building.OfficeBuildingBuilder;
import ua.lpnu.security_system_simulator.repository.BuildingRepository;

import java.util.List;

@RestController
@CrossOrigin
@Tag(name="Buildings")
public class BuildingController {
    BuildingRepository repository;

    @Autowired
    public BuildingController(BuildingRepository repository){
        this.repository = repository;
    }

    @Operation(
            description = "Returns list of all saved buildings.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No Content",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content
                    )
            }
    )
    @GetMapping("/buildings")
    public ResponseEntity<List<BuildingLevel>> getAllBuildings(){
        try {
           return repository.count()==0
                    ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                    : new ResponseEntity<>(repository.idAndName(), HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            description = "Returns a specific item by its id.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No Content",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content
                    )
            },
            parameters = @Parameter(name = "id", description = "building's id", example="657724992d41152fbd659219")
    )
    @GetMapping("/buildings/{id}")
    public ResponseEntity<BuildingLevel> getBuilding(@PathVariable("id") String id) {
        try {
            var result = repository.findById(id);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", result);

            return result.isPresent()
                    ? new ResponseEntity<>(result.get(), HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            description = "Adds a new custom building.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "406",
                            description = "Not Acceptable",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Conflict",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content
                    )
            }
    )
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

    @Operation(
            description = "Updates a specific item by its id.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Conflict",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content
                    )
            },
            parameters = @Parameter(name = "id", description = "building's id", example="657724992d41152fbd659219")
    )
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

    @Operation(
            description = "Deletes a specific item by its id.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content
                    )
            },
            parameters = @Parameter(name = "id", description = "building's id", example="657724992d41152fbd659219")
    )
    @DeleteMapping("/buildings/{id}")
    public ResponseEntity<BuildingLevel> deleteBuilding(@PathVariable("id") String id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            description = "Adds a new residential building.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Conflict",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Unprocessable entity",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content
                    )
            }
    )
    @PostMapping("/buildings/residential")
    public  ResponseEntity<BuildingLevel> generateApartmentBuilding(HttpEntity<String> httpEntity){
        try {
            BuildingLevel building = build(new ApartmentBuildingBuilder(), new JSONObject(httpEntity.getBody()));
            var result = repository.save(building);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (DuplicateKeyException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (JSONException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Operation(
            description = "Adds a new office building.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Conflict",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Unprocessable entity",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content
                    )
            }
    )
    @PostMapping("/buildings/office")
    public  ResponseEntity<BuildingLevel> generateOfficeBuilding(HttpEntity<String> httpEntity){
        try {
            BuildingLevel building = build(new OfficeBuildingBuilder(), new JSONObject(httpEntity.getBody()));
            var result = repository.save(building);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (DuplicateKeyException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (JSONException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private BuildingLevel build(BuildingBuilder builder, JSONObject json) throws Exception {
        builder.seNumberOfFloors(Integer.parseInt(json.get("floors").toString()));
        builder.setNumberOfRoomsPerFloor(Integer.parseInt(json.get("rooms").toString()));
        builder.setName(json.get("name").toString());
        return builder.build();
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
