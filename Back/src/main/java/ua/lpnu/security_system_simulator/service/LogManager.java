package ua.lpnu.security_system_simulator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import ua.lpnu.security_system_simulator.model.building.BuildingLevel;
import ua.lpnu.security_system_simulator.model.building.Room;
import ua.lpnu.security_system_simulator.model.event.DangerLevel;
import ua.lpnu.security_system_simulator.model.event.Event;
import ua.lpnu.security_system_simulator.model.event.EventFactory;
import ua.lpnu.security_system_simulator.model.event.EventType;
import ua.lpnu.security_system_simulator.model.system.EventLog;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class LogManager {
    private final EventFactory eventFactory;

    @Autowired
    public LogManager(EventFactory eventFactory) {
        this.eventFactory = eventFactory;
    }


    public void createNewLog(BuildingLevel building) {
        getRooms(building).sorted(Comparator.comparingInt(Room::getRoomNumber)).forEach(Room::createNewLog);
    }

    public void startLog(BuildingLevel building) throws InterruptedException {
        Room firstRoom = getRooms(building).sorted(Comparator.comparingInt(Room::getRoomNumber)).toList().getFirst();
        if (firstRoom != null) {
            new EventFactory().createEvent(EventType.SIMULATION_START, firstRoom, DangerLevel.LOW);
        } else {
            throw new NullPointerException();
        }
    }

    public List<List<Event>> getAllLogs(BuildingLevel building) {
        var listOfLists = getRooms(building).map(Room::getLogs).toList();

        List<List<Event>> resultList = new ArrayList<>();
        for (int i = 0; listOfLists.size() > i && i != listOfLists.get(i).size(); ++i) {
            List<EventLog> toInsert = new ArrayList<>();
            for (int j = 0; j != listOfLists.size(); ++j) {
                if (listOfLists.get(j).size() > i && !listOfLists.get(j).get(i).getEvents().isEmpty()) {
                    toInsert.add(listOfLists.get(j).get(i));
                }
            }
            if (!toInsert.isEmpty()) {
                resultList.add(toInsert.stream().map(EventLog::getEvents).flatMap(List::stream).toList());
            }
        }
        return resultList;
    }

    public List<Event> getLog(BuildingLevel building, int index) {
        var logs = getAllLogs(building);

        List<List<Event>> events = new ArrayList<>();

        for (int i = index; i >= 0 && !logs.get(i).isEmpty() && ((logs.get(i).getFirst()
                .getEventType() != EventType.SIMULATION_START) || (events.isEmpty() && logs.get(i).getFirst()
                .getEventType() == EventType.SIMULATION_START)); --i) {
            events.add(logs.get(i));
        }

        return events.stream().flatMap(List::stream).toList();
    }

    public List<Event> getLogSinceStart(BuildingLevel building, int index) {
        var logs = getAllLogs(building);

        List<List<Event>> events = new ArrayList<>();
        for (int i = index; i > 0 && logs.get(i).get(0).getEventType() != EventType.SIMULATION_START; --i) {
            events.add(logs.get(i));
        }
        if (events.isEmpty()) {
            events.add(logs.get(index));
        }
        return events.stream().flatMap(List::stream).toList();
    }

    public BuildingLevel rollbackToIndex(BuildingLevel building, int index) {
        getAllLogs(building);
        for (var room : getRooms(building).toList()) {
            room.rollback(index);
        }
        return building;
    }

    public int getLogsCount(BuildingLevel building) {
        return StreamSupport.stream(building.depthFirstSearch().spliterator(), false)
                .filter(component -> component instanceof Room)
                .mapToInt(component -> (((Room) component).getLogs().size())).max().orElse(0);
    }

    public List<Event> getEventsOnFloor(BuildingLevel building) {
        return getRooms(building).map(Room::getLogs).flatMap(List::stream).map(EventLog::getEvents)
                .flatMap(List::stream).toList();
    }

    public BuildingLevel saveLog(BuildingLevel building, JSONArray events) throws JSONException, ParseException, IOException {
        createNewLog(building);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        for (int i = 0; i != events.length(); ++i) {
            JSONObject event = events.getJSONObject(i);
            for (var room : getRooms(building).toList()) {
                if (event.getInt("location") == room.getRoomNumber()) {
                    var eventType = Arrays.stream(EventType.values()).filter(type -> {
                        try {
                            return type.toString().equals(event.getString("eventType"));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }).findFirst().orElseThrow(() -> new IOException("Bad enum value"));
                    var dangerLevel = Arrays.stream(DangerLevel.values()).filter(type -> {
                        try {
                            return type.toString().equals(event.getString("dangerLevel"));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }).findFirst().orElseThrow(() -> new IOException("Bad enum value"));
                    var happenedAt = formatter.parse(event.getString("happenedAt"));
                    eventFactory.createEvent(eventType, room, dangerLevel, happenedAt, event.getBoolean("result"));
                    break;
                }
            }
        }
        return building;
    }

    private Stream<Room> getRooms(BuildingLevel building) {
        return StreamSupport.stream(building.depthFirstSearch().spliterator(), false).filter(Room.class::isInstance)
                .map(Room.class::cast);
    }
}
