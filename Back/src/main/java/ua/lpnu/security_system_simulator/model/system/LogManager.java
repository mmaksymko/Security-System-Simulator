package ua.lpnu.security_system_simulator.model.system;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ua.lpnu.security_system_simulator.model.building.BuildingComponent;
import ua.lpnu.security_system_simulator.model.building.BuildingLevel;
import ua.lpnu.security_system_simulator.model.building.Room;
import ua.lpnu.security_system_simulator.model.event.DangerLevel;
import ua.lpnu.security_system_simulator.model.event.Event;
import ua.lpnu.security_system_simulator.model.event.EventFactory;
import ua.lpnu.security_system_simulator.model.event.EventType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.StreamSupport;

@Component
public class LogManager {
    public void createNewLog(BuildingLevel building) {
        StreamSupport.stream(building.depthFirstSearch().spliterator(), false)
                .filter(component -> component instanceof Room)
                .map(component -> (Room) component)
                .sorted(Comparator.comparingInt(Room::getRoomNumber))
                .forEach(Room::createNewLog);
    }

    public void startLog(BuildingLevel building){
        Room firstRoom = StreamSupport.stream(building.depthFirstSearch().spliterator(), false)
                .filter(component -> component instanceof Room)
                .map(component -> (Room) component)
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
        var listOfLists = StreamSupport.stream(building.depthFirstSearch().spliterator(), false)
                .filter(Room.class::isInstance)
                .map(component -> ((Room) component).getLogs())
                .toList();

        List<List<Event>> resultList = new ArrayList<>();
        for (int i = 0; i!=listOfLists.get(i).size();++i) {
            List<EventLog> toInsert = new ArrayList<>();
            for(int j = 0; j!= listOfLists.size(); ++j){
                if (!listOfLists.get(j).get(i).getEvents().isEmpty()) {
                    toInsert.add(listOfLists.get(j).get(i));
                }
            }
            resultList.add(toInsert.stream().map(EventLog::getEvents).flatMap(List::stream).toList());
        }
        return resultList;
    }

    public List<Event> getLog(BuildingLevel building, int index){
        var logs = getAllLogs(building);

        List<List<Event>> events = new ArrayList<>();

        for(int i = index; i>=0 && !logs.get(i).isEmpty() && ((logs.get(i).get(0).getEventType() != EventType.SIMULATION_START)
                || (events.isEmpty() && logs.get(i).get(0).getEventType() == EventType.SIMULATION_START)); --i){
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
        long count = getAllLogs(building).stream().flatMap(List::stream).filter(log -> log.getEventType()==EventType.SIMULATION_START).count();


//        new ArrayList<>().stream().mapToInt()

        System.out.println(count);
//        if (index >= count) {
//            throw new IllegalArgumentException();
//        }

        List<Integer> indices = new ArrayList<>();
        int counter = 0;
        for (var component : building.depthFirstSearch()){
            if (component instanceof Room room){
                for (int i = 0; i!=room.getLogs().size(); ++i){
                    if(!room.getLogs().isEmpty()) {
                        if (!room.getLogs().get(i).getEvents().isEmpty() && room.getLogs().get(i).getEvents().get(0)
                                .getEventType() == EventType.SIMULATION_START) {
                            indices.add(i);
                            counter++;
                            System.out.println(i);
                        }
                    }
                }
                indices.add(room.getLogs().size());
            }
        }
//
//      //  indices.forEach(System.out::println);
//
//        for (var component : building.depthFirstSearch()){
//            if (component instanceof Room room){
//                for (int i = 0; i!=room.getLogs().size(); ++i){
//                    if(!room.getLogs().isEmpty()) {
//                        if (indices.get(index+1)<=i) {
//                            System.out.println("m");
//                            room.removeLogsFromIndex(i);
//                        }
//                    }
//                }
//            }
//        }


//        var logs = StreamSupport.stream(building.depthFirstSearch().spliterator(), false)
//                .filter(Room.class::isInstance)
//                .map(component -> ((Room) component).getLogs()).flatMap(List::stream).toList();
//
//        List<Integer> indicesOfSimulation = new ArrayList<>();
//        for (int i = 0; i != logs.size(); ++i){
//            if (!logs.get(i).getEvents().isEmpty() && logs.get(i).getEvents().get(0).getEventType() == EventType.SIMULATION_START) {
//                indicesOfSimulation.add(i);
//            }
//        }
//         var realIndex = (index+1 == indicesOfSimulation.size() ? logs.size() : indicesOfSimulation.get(index+1));
//        System.out.println(logs.size());
//        System.out.println('-');
//        indicesOfSimulation.forEach(System.out::println);
//
//
//        for (var log : logs) {
//            for(int i = log.size()-1; i>=realIndex; --i){
//                log.remove(index);
//            }
//        }
        return building;
    }

    private List<Integer> indicesOfSimulations(BuildingLevel building){
        var logs = getAllLogs(building);
        var indices = new ArrayList<Integer>();
        for (int i = 0; i!=logs.size(); ++i) {
            if (!logs.get(i).isEmpty() && logs.get(i).get(0).getEventType() == EventType.SIMULATION_START) {
                indices.add(i);
            }
        }
        return indices;
    }

    public int getLogsCount(BuildingLevel building) {
        return StreamSupport.stream(building.depthFirstSearch().spliterator(), false)
                .filter(component -> component instanceof Room)
                .mapToInt(component -> (((Room) component).getLogs().size()))
                .max()
                .orElse(0);
    }
}
