package ua.lpnu.security_system_simulator.model.building.build;

import ua.lpnu.security_system_simulator.model.building.Room;
import ua.lpnu.security_system_simulator.model.sensor.Sensor;

public class OfficeRoomBuilder implements RoomBuilder{
    private Room result;
    @Override
    public void reset() {
        this.result = new Room();
    }
    @Override
    public void setArea(int area) {
        result.setArea(area);
    }
    @Override
    public void setRoomNumber(int roomNumber) {
        result.setRoomNumber(roomNumber);
    }
    @Override
    public void addWindow(int numberOfWindows) {
        result.addWindows(numberOfWindows);
    }
    @Override
    public void addDoor(int numberOfDoors) {
        result.addDoors(numberOfDoors);
    }
    @Override
    public void addSensor(Sensor sensor) {
        result.addSensor(sensor);
    }
    @Override
    public Room getResult() {
        return null;
    }
}
