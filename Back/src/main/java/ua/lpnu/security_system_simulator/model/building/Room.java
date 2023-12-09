package ua.lpnu.security_system_simulator.model.building;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.lpnu.security_system_simulator.model.event.Event;
import ua.lpnu.security_system_simulator.model.event.EventTarget;
import ua.lpnu.security_system_simulator.model.event.EventType;
import ua.lpnu.security_system_simulator.model.sensor.Sensor;
import ua.lpnu.security_system_simulator.model.system.EventLog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Document
public class Room implements BuildingComponent, EventTarget {
    @Id
    private String id;
    private RoomType roomType;
    private int roomNumber;
    private int area;
    private int windows;
    private int doors;
    private List<Sensor> sensors;
    private List<EventLog> logs;

    public Room() {
        this.id = new ObjectId().toString();
        sensors = new ArrayList<>();
        logs = new ArrayList<>();
    }

    public Room(RoomType roomType, int roomNumber, int area, int windows, int doors, List<Sensor> sensors, List<EventLog> logs) throws IllegalArgumentException {
        if (roomNumber < 0 || area <= 0 || windows < 0 || doors < 0){
            throw new IllegalArgumentException("Invalid room parameters");
        }
        this.roomType = roomType;
        this.roomNumber = roomNumber;
        this.area = area;
        this.windows = windows;
        this.doors = doors;
        this.sensors = sensors;
        this.logs = logs;
        this.id = new ObjectId().toString();
    }

    private boolean validateSensorCount(Sensor sensor) {
        EventType type = sensor.getType();

        if(type!=EventType.OPENED_DOOR && type!=EventType.OPENED_WINDOW){
            return true;
        }

        int openablesCount = (type == EventType.OPENED_WINDOW) ? windows : doors;

        return getAllSensorsOfType(type).size() < openablesCount;
    }

    public void addSensor(Sensor sensor) throws IllegalArgumentException{
        if (!validateSensorCount(sensor))
            throw new IllegalArgumentException("More sensors than openables");
        sensors.add(sensor);
    }

    @Override
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public RoomType getRoomType() {
        return roomType;
    }
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
    public int getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(int roomNumber) throws IllegalArgumentException {
        if (roomNumber < 0){
            throw new IllegalArgumentException("Invalid room number");
        }this.roomNumber = roomNumber;
    }
    public int getArea() {
        return area;
    }
    public void setArea(int area) throws IllegalArgumentException {
        if (area <= 0){
            throw new IllegalArgumentException("Invalid area");
        }this.area = area;
    }
    public int getWindows() {
        return windows;
    }
    public void setWindows(int windows) throws IllegalArgumentException {
        if (windows < 0){
            throw new IllegalArgumentException("Invalid windows quantity");
        }
        this.windows = windows;
    }
    public int getDoors() {
        return doors;
    }
    public void setDoors(int doors) throws IllegalArgumentException {
        if (doors < 0){
            throw new IllegalArgumentException("Invalid doors quantity");
        }
        this.doors = doors;
    }

    public List<Sensor> getSensors() {
        return Collections.unmodifiableList(sensors);
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    public List<EventLog> getLogs() {
        return Collections.unmodifiableList(logs);
    }

    @JsonIgnore
    public EventLog getLastLog() {
        if (logs.isEmpty())
            logs.add(new EventLog());
        return logs.getLast();
    }

    public void setLogs(List<EventLog> logs) {
        this.logs = logs;
    }

    public void removeSensor(Sensor sensor){
        sensors.remove(sensor);
    }

    public int sensorCount(){
        return sensors.size();
    }

    @JsonIgnore
    public List<Sensor> getAllSensorsOfType(EventType type) {
        return sensors.stream().filter(sensor -> sensor.getType().equals(type)).toList();
    }

    @Override
    public void registerEvent(Event event){
        this.getLastLog().log(event);
    }

    @Override
    public void addComponent(BuildingComponent child) {
        throw new UnsupportedOperationException("Rooms cannot have children.");
    }

    @Override
    public void removeComponent(BuildingComponent child) {
        throw new UnsupportedOperationException("Rooms cannot have children.");
    }

    @Override
    public List<BuildingComponent> getComponents() {
        return new ArrayList<>();
    }

    @Override
    public int getNumberOfComponents() {
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Room room = (Room) o;
        return roomNumber == room.roomNumber && area == room.area && windows == room.windows && doors == room.doors && Objects.equals(
                id, room.id) && roomType == room.roomType && Objects.equals(sensors, room.sensors) && Objects.equals(
                logs, room.logs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomType, roomNumber, area, windows, doors, sensors, logs);
    }

    @Override
    public String toString(){
        return "Room №" + roomNumber + " with " + sensorCount() + " sensors";
    }
}