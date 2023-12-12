package ua.lpnu.security_system_simulator.service;

import org.springframework.stereotype.Service;
import ua.lpnu.security_system_simulator.model.building.BuildingLevel;
import ua.lpnu.security_system_simulator.model.building.Room;
import ua.lpnu.security_system_simulator.model.event.DangerLevel;
import ua.lpnu.security_system_simulator.model.event.Event;
import ua.lpnu.security_system_simulator.model.event.EventFactory;
import ua.lpnu.security_system_simulator.model.event.EventType;
import ua.lpnu.security_system_simulator.model.system.EventLog;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class LogManager {
    public void createNewLog(BuildingLevel building) {
       getRooms(building)
               .sorted(Comparator.comparingInt(Room::getRoomNumber))
               .forEach(Room::createNewLog);
    }

    public void startLog(BuildingLevel building) throws InterruptedException {
        Room firstRoom = getRooms(building)
                .sorted(Comparator.comparingInt(Room::getRoomNumber))
                .toList()
                .getFirst();
        if (firstRoom!=null) {
            new EventFactory().createEvent(EventType.SIMULATION_START, firstRoom, DangerLevel.LOW);
        } else {
            throw new NullPointerException();
        }
    }

    public List<List<Event>> getAllLogs(BuildingLevel building) {
        var listOfLists = getRooms(building)
                .map(Room::getLogs)
                .toList();

        List<List<Event>> resultList = new ArrayList<>();
        for (int i = 0; i!=listOfLists.get(i).size();++i) {
            List<EventLog> toInsert = new ArrayList<>();
            for(int j = 0; j!= listOfLists.size(); ++j){
                if (!listOfLists.get(j).get(i).getEvents().isEmpty()) {
                    toInsert.add(listOfLists.get(j).get(i));
                }
            }
            if (!toInsert.isEmpty()) {
                resultList.add(toInsert.stream().map(EventLog::getEvents).flatMap(List::stream).toList());
            }
        }
        return resultList;
    }

    public List<Event> getLog(BuildingLevel building, int index){
        var logs = getAllLogs(building);

        List<List<Event>> events = new ArrayList<>();

        for(int i = index; i>=0 && !logs.get(i).isEmpty() && ((logs.get(i).getFirst().getEventType() != EventType.SIMULATION_START)
                || (events.isEmpty() && logs.get(i).getFirst().getEventType() == EventType.SIMULATION_START)); --i){
            events.add(logs.get(i));
        }

        return events.stream().flatMap(List::stream).toList();
    }

    public List<Event> getLogSinceStart(BuildingLevel building, int index){
        var logs = getAllLogs(building);
        var indices = indicesOfSimulations(building);
        if(indices.size()<=index){
            throw new IndexOutOfBoundsException();
        }
        List<List<Event>> events = new ArrayList<>();
        for (int i = indices.get(index); i != (index+1 == indices.size() ? logs.size() : indices.get(index+1)); ++i) {
            events.add(logs.get(i));
        }
        return events.stream().flatMap(List::stream).toList();
    }

    public BuildingLevel rollbackToIndex(BuildingLevel building, int index){
        getAllLogs(building);
        for (var room : getRooms(building).toList()){
            room.rollback(index);
        }
        return building;
    }

    public int getLogsCount(BuildingLevel building) {
        return StreamSupport.stream(building.depthFirstSearch().spliterator(), false)
                .filter(component -> component instanceof Room)
                .mapToInt(component -> (((Room) component).getLogs().size()))
                .max()
                .orElse(0);
    }
    public List<Event> getEventsOnFloor(BuildingLevel building){
        return getRooms(building)
                .map(Room::getLogs)
                .flatMap(List::stream)
                .map(EventLog::getEvents)
                .flatMap(List::stream)
                .toList();
    }

    private List<Integer> indicesOfSimulations(BuildingLevel building){
        var logs = getAllLogs(building);
        var indices = new ArrayList<Integer>();
        for (int i = 0; i!=logs.size(); ++i) {
            if (!logs.get(i).isEmpty() && logs.get(i).getFirst().getEventType() == EventType.SIMULATION_START) {
                indices.add(i);
            }
        }
        return indices;
    }

    private Stream<Room> getRooms(BuildingLevel building){
        return StreamSupport.stream(building.depthFirstSearch().spliterator(), false)
                .filter(Room.class::isInstance)
                .map(Room.class::cast);
    }
}
