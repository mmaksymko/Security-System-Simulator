package ua.lpnu.security_system_simulator.model.building.builder;

import ua.lpnu.security_system_simulator.model.building.Room;
import ua.lpnu.security_system_simulator.model.building.RoomType;
import ua.lpnu.security_system_simulator.model.sensor.Sensor;

//TODO manual room builder????
public interface Builder {
    public void setRoomType(RoomType roomType);
    public void setArea(int area) throws Exception;
    public void setRoomNumber(int roomNumber) throws Exception;
    public void setWindows(int numberOfWindows) throws Exception;
    public void setDoors(int numberOfDoors) throws Exception;
    public void addSensor(Sensor sensor) throws Exception;
    public Room getResult() throws Exception;
}