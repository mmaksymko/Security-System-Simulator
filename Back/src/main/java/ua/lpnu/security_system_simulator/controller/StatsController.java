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
import ua.lpnu.security_system_simulator.model.event.DangerLevel;
import ua.lpnu.security_system_simulator.model.event.EventType;
import ua.lpnu.security_system_simulator.service.EventStatistics;
import ua.lpnu.security_system_simulator.repository.BuildingRepository;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("stats")
@Tag(name = "Statistics")
public class StatsController {
    BuildingRepository repository;
    EventStatistics statistics;

    @Autowired
    public StatsController(BuildingRepository repository, EventStatistics statistics){
        this.repository = repository;
        this.statistics = statistics;
    }
    @Operation(
            description = "Returns specific event per building statistics.",
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
            parameters = {
                    @Parameter(name = "id", description = "log's index", example="657724992d41152fbd659219"),
            }
    )
    @GetMapping("/even/building/{id}")
    public ResponseEntity<Map<EventType, Long>> eventTypePerBuilding(@PathVariable("id") String id){
        try {
            return new ResponseEntity<>(statistics.eventTypePerBuilding(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            description = "Returns dangerLevel per building statistics.",
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
            parameters = {
                    @Parameter(name = "id", description = "log's index", example="657724992d41152fbd659219"),
            }
    )
    @GetMapping("/danger/building/{id}")
    public ResponseEntity<Map<DangerLevel, Long>> dangerLevelPerBuilding(@PathVariable("id") String id){
        try {
            return new ResponseEntity<>(statistics.dangerLevelPerBuilding(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(
            description = "Returns location per building statistics.",
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
            parameters = {
                    @Parameter(name = "id", description = "log's index", example="657724992d41152fbd659219"),
            }
    )
    @GetMapping("/location/events/{id}")
    public ResponseEntity<Map<String, Long>> locationsWithEvents(@PathVariable("id") String id){
        try {
            return new ResponseEntity<>(statistics.eventsPerFloor(id), HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
