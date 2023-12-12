package ua.lpnu.security_system_simulator.model.stats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;
import ua.lpnu.security_system_simulator.model.building.BuildingComponent;
import ua.lpnu.security_system_simulator.model.building.BuildingIterator;
import ua.lpnu.security_system_simulator.model.building.BuildingLevel;
import ua.lpnu.security_system_simulator.model.building.Room;
import ua.lpnu.security_system_simulator.model.event.DangerLevel;
import ua.lpnu.security_system_simulator.model.event.Event;
import ua.lpnu.security_system_simulator.model.event.EventType;
import ua.lpnu.security_system_simulator.model.system.EventLog;
import ua.lpnu.security_system_simulator.model.system.LogManager;
import ua.lpnu.security_system_simulator.repository.BuildingRepository;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class EventStatistics {
    private LogManager logManager;
    private BuildingRepository repository;
    @Autowired
    public EventStatistics(LogManager logManager, BuildingRepository repository){
        this.logManager = logManager;
        this.repository = repository;
    }
    // тип івенту - кількість (по логах за всі симуляції)
    public Map<EventType, Long> eventTypePerBuilding(String id){
        Map<EventType, Long> result;
        BuildingLevel building = repository.findById(id).get();
        List<List<Event>> events = logManager.getAllLogs(building);
        result = events.stream()
                .flatMap(Collection::stream)
                .filter(e -> !e.getEventType().toString().contains("REACTION") && !e.getEventType().toString().contains("SIMULATION_START"))
                .collect(Collectors.groupingBy(Event::getEventType, Collectors.counting()));
        return result;
    }
    // рівень небезпеки - кількість (по логах за всі симуляції)
    public Map<DangerLevel, Long> dangerLevelPerBuilding(String id) throws Exception{
        Map<DangerLevel, Long> result;
        if(repository.findById(id).isEmpty()){
            throw new Exception("Building does not exist");
        }
        BuildingLevel building = repository.findById(id).get();
        List<List<Event>> events = logManager.getAllLogs(building);
        result = events.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Event::getDangerLevel, Collectors.counting()));
        return result;
    }
    // поверх - кількість івентів (за всі симіляції)
    public Map<String, Long> eventsPerFloor(String id) throws Exception{
        Map<String, Long> result = new TreeMap<>();
        if(repository.findById(id).isEmpty()){
            throw new Exception("Building does not exist");
        }
        BuildingLevel building = repository.findById(id).get();
        BuildingIterator iterator = new BuildingIterator(building, true);
        iterator.next();
        while (iterator.hasNext()){
            BuildingComponent currentBuildingLevel = iterator.next();
            if(currentBuildingLevel instanceof Room){
                break;
            }
            BuildingLevel currentFloor = (BuildingLevel)currentBuildingLevel;
            List<Event> events = logManager.getEventsOnFloor(currentFloor);
            result.put(currentFloor.getName(), (long) events.size());
        }

        return result;
    }
}
