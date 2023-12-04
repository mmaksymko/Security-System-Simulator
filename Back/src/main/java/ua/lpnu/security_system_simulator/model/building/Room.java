package ua.lpnu.security_system_simulator.model.building;

import ua.lpnu.security_system_simulator.model.event.EventType;
import ua.lpnu.security_system_simulator.model.sensor.Sensor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Room implements BuildingComponent {
    private final RoomType roomType;
    private final int roomNumber;
    private final int area;
    private final int windows;
    private final int doors;
    private final List<Sensor> sensors;



    public Room(RoomType roomType, int roomNumber, int area, int windows, int doors, List<Sensor> sensors) {
        if (roomNumber < 0 || area <= 0 || windows < 0 || doors < 0){
            throw new IllegalArgumentException("Invalid room parameters");
        }
        this.roomType = roomType;
        this.roomNumber = roomNumber;
        this.area = area;
        this.windows = windows;
        this.doors = doors;
        this.sensors = sensors;
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

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getArea() {
        return area;
    }

    public int getWindows() {
        return windows;
    }

    public int getDoors() {
        return doors;
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

    public void removeSensor(Sensor sensor){
        sensors.remove(sensor);
    }

    public int sensorCount(){
        return sensors.size();
    }

    public List<Sensor> getAllSensorsOfType(EventType type) {
        return sensors.stream().filter(sensor -> sensor.getType().equals(type)).toList();
    }

    @Override
    public String toString(){
        return "Room №" + roomNumber + " with " + sensorCount() + " sensors";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomNumber == room.roomNumber && area == room.area && windows == room.windows && doors == room.doors && roomType == room.roomType && Objects.equals(sensors, room.sensors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomType, roomNumber, area, windows, doors, sensors);
    }
}