package ua.lpnu.security_system_simulator.model.building;

import ua.lpnu.security_system_simulator.model.event.EventType;
import ua.lpnu.security_system_simulator.model.sensor.Sensor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
public class Room implements BuildingComponent {
    private int roomNumber;
    private int area;
    private int windows;
    private int doors;
    private List<Sensor> sensors;

    public Room(int roomNumber, int area, int windows, int doors) {
        if (roomNumber < 0 || area <= 0 || windows < 0 || doors < 0){
            throw new IllegalArgumentException("Invalid room parameters");
        }

        this.roomNumber = roomNumber;
        this.area = area;
        this.windows = windows;
        this.doors = doors;
        sensors = new LinkedList<>();
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

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public void setWindows(int windows) {
        this.windows = windows;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
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
}