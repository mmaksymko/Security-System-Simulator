package ua.lpnu.security_system_simulator.model.building.build;

import ua.lpnu.security_system_simulator.model.building.Room;

public class ApartmentRoomBuilder implements RoomBuilder {
    private Room result;
    @Override
    public void reset() {
        this.result = new Room();
    }
    @Override
    public void setArea(int area) {
        result.se
    }
    @Override
    public void setRoomNumber(int area) {

    }
    @Override
    public void addWindow() {

    }
    @Override
    public void addDoor() {

    }
    @Override
    public void addSensor() {

    }
    @Override
    public Room getResult() {
        return null;
    }
}
