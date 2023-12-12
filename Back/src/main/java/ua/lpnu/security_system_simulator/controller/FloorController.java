package ua.lpnu.security_system_simulator.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lpnu.security_system_simulator.model.building.BuildingComponent;
import ua.lpnu.security_system_simulator.model.building.BuildingLevel;
import ua.lpnu.security_system_simulator.repository.BuildingRepository;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@CrossOrigin
@Tag(name="Floors")
public class FloorController {
    BuildingRepository repository;

    @Autowired
    public FloorController(BuildingRepository repository){
        this.repository = repository;
    }

    @Operation(
            description = "Get all specific floor of a specific building by it's id.",
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
            parameters = @Parameter(name = "id", description = "building's id", example="657724992d41152fbd659220")
    )
    @GetMapping("/buildings/{id}/floors")
    public ResponseEntity<List<BuildingComponent>> getAllFloors(@PathVariable("id") String id){
        try {
            var result = repository.findById(id);
            return result.isPresent()
                      ? new ResponseEntity<>(result.get().getComponents(), HttpStatus.OK)
                      : new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            description = "Get a specific floor by it's id.",
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
            parameters = @Parameter(name = "floorId", description = "floor's id", example="657724992d41152fbd659220")
    )
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
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            description = "Get a specific floor by it's id and building's id.",
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
            parameters = {
                    @Parameter(name = "buildingId", description = "building's id", example="657724992d41152fbd659219"),
                    @Parameter(name = "floorId", description = "floor's id", example="657724992d41152fbd659220")
            }
    )
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

    @Operation(
            description = "Add a floor to the building.",
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
            },
            parameters = @Parameter(name = "id", description = "building's id", example="657724992d41152fbd659220")
    )
    @PostMapping("/buildings/{id}/")
    public ResponseEntity<BuildingLevel> addFloor(@RequestBody BuildingLevel buildingLevel, @PathVariable("id") String id) {
        try {
            if(!validateFloor(buildingLevel)){
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }

            var optionalBuilding = repository.findById(id);
            if(optionalBuilding.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            var building = optionalBuilding.get();
            if (building.getNumberOfComponents() >= 200){
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }

            building.getComponents().add(buildingLevel);
            repository.save(building);
            return new ResponseEntity<>(building, HttpStatus.OK);
        } catch (DuplicateKeyException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            description = "Update a specific floor by it's id and building's id.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No Content",
                            content = @Content
                    ),  @ApiResponse(
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
            },
            parameters = @Parameter(name = "floorId", description = "floor's id", example="657724992d41152fbd659220")
    )
    @PutMapping("/floors/{floorId}")
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
        } catch (DuplicateKeyException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(
            description = "Update a specific floor by it's id and building's id.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "204",
                            description = "No Content",
                            content = @Content
                    ),  @ApiResponse(
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
            },
            parameters = {
                    @Parameter(name = "buildingId", description = "building's id", example="657724992d41152fbd659219"),
                    @Parameter(name = "floorId", description = "floor's id", example="657724992d41152fbd659220")
            }
    )
    @PutMapping("/buildings/{buildingId}/floors/{floorId}")
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
        } catch (DuplicateKeyException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            description = "Delete a specific floor by it's id.",
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
            parameters = @Parameter(name = "floorId", description = "floor's id", example="657724992d41152fbd659220")
    )
    @DeleteMapping("/floors/{floorId}")
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

    @Operation(
            description = "Delete a specific floor by it's id and building's id.",
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
            parameters = {
                    @Parameter(name = "buildingId", description = "building's id", example="657724992d41152fbd659219"),
                    @Parameter(name = "floorId", description = "floor's id", example="657724992d41152fbd659220")
            }
    )
    @DeleteMapping("/buildings/{buildingId}/floors/{floorId}")
    public ResponseEntity<BuildingLevel> updateFloor(@PathVariable("buildingId") String buildingId, @PathVariable("floorId") String floorId){
        try {
            var building = repository.findById(buildingId);
            if (building.isPresent()) {
                var floor = building.get().getComponents().stream().filter(level -> level.getId().equals(floorId)).findFirst();
                if (floor.isPresent()) {
                    return deleteFloor(building.get(), floor.get());
                }
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
