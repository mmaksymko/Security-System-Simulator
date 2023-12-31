package ua.lpnu.security_system_simulator.model.building.builder.room;

import ua.lpnu.security_system_simulator.model.building.Room;
import ua.lpnu.security_system_simulator.model.building.RoomType;
import ua.lpnu.security_system_simulator.model.sensor.Sensor;

//TODO manual room builder????
public interface RoomBuilder {
    void setRoomType(RoomType roomType);
    void setWidth(int width) throws IllegalArgumentException;
    void setLength(int length) throws IllegalArgumentException;
    void setRoomNumber(int roomNumber) throws IllegalArgumentException;
    void setWindows(int numberOfWindows) throws IllegalArgumentException;
    void setDoors(int numberOfDoors) throws IllegalArgumentException;
    void addSensor(Sensor sensor) throws IllegalArgumentException;
    int getArea();
    Room build() throws IllegalArgumentException;
    void reset();
}