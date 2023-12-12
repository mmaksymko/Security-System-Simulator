package ua.lpnu.security_system_simulator.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lpnu.security_system_simulator.service.LogManager;
import ua.lpnu.security_system_simulator.model.event.Event;
import ua.lpnu.security_system_simulator.repository.BuildingRepository;

import java.util.List;

@RestController
@CrossOrigin
@Tag(name="Logs")
public class LogController {
    private final BuildingRepository repository;
    private final LogManager logManager;

    @Autowired
    public LogController(BuildingRepository repository, LogManager logManager){
        this.repository = repository;
        this.logManager = logManager;
    }

    @Operation(
            description = "Returns a log of a specific building its id.",
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
    @GetMapping("buildings/{id}/logs")
    public ResponseEntity<List<List<Event>>> getLogs(@PathVariable("id") String id){
        try {
            var result = repository.findById(id);
            return result.isPresent()
                    ? new ResponseEntity<>(logManager.getAllLogs(result.get()), HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            description = "Returns a specific log instance by index id and building's id.",
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
                    @Parameter(name = "id", description = "building's id", example="657724992d41152fbd659219"),
                    @Parameter(name = "index", description = "index of saved log", example="2")
            }
    )
    @GetMapping("buildings/{id}/logs/{index}")
    public ResponseEntity<List<Event>> getLog(@PathVariable("id") String id, @PathVariable("index") int index){
        try {
            var result = repository.findById(id);
            return result.isPresent()
                    ? new ResponseEntity<>(logManager.getLog(result.get(), index), HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            description = "Rolls back to a specific log by its index and building's id.",
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
                    @Parameter(name = "id", description = "building's id", example="657724992d41152fbd659219"),
                    @Parameter(name = "index", description = "index of saved log", example="2")
            }
    )
    @PostMapping("buildings/{id}/logs/{index}")
    public ResponseEntity<List<Event>> rollbackToLog(@PathVariable("id") String id, @PathVariable("index") int index) {
        try {
            var optionalBuilding = repository.findById(id);
            if (optionalBuilding.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            var building = logManager.rollbackToIndex(optionalBuilding.get(), index);
            var result = repository.save(building);
            repository.deleteById(building.getId());
            repository.save(building);
            return new ResponseEntity<>(logManager.getLogSinceStart(building, index), HttpStatus.OK);
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
   }
}