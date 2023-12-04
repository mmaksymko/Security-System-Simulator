package ua.lpnu.security_system_simulator.model.building.build;

import ua.lpnu.security_system_simulator.model.building.Room;
import ua.lpnu.security_system_simulator.model.sensor.Sensor;

public interface RoomBuilder{
    public void reset();
    public void setArea(int area);
    public void setRoomNumber(int number);
    public void addWindow(int numberOfWindows);
    public void addDoor(int numberOfWindows);
    public void addSensor(Sensor sensor);
    public Room getResult();
}