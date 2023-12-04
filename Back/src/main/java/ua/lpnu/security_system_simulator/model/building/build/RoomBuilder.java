package ua.lpnu.security_system_simulator.model.building.build;

import ua.lpnu.security_system_simulator.model.building.Room;

public interface RoomBuilder{
    public void reset();
    public void setArea(int area);
    public void setRoomNumber(int number);
    public void addWindow();
    public void addDoor();
    public void addSensor();
    public Room getResult();
}