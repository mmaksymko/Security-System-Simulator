package ua.lpnu.security_system_simulator.model.building.builder;

import ua.lpnu.security_system_simulator.model.building.Room;
import ua.lpnu.security_system_simulator.model.building.RoomType;
import ua.lpnu.security_system_simulator.model.sensor.Sensor;

import java.util.LinkedList;
import java.util.List;

public class RoomBuilder implements Builder{
    private RoomType roomType;
    private int roomNumber;
    private int area;
    private int windows;
    private int doors;
    private List<Sensor> sensors;
    RoomBuilder(){
        sensors = new LinkedList<>();
    }

    @Override
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public void setArea(int area) throws Exception{
        if(area <= 0){
            throw new Exception("Area must be greater than 0.");
        }
        this.area = area;
    }

    @Override
    public void setRoomNumber(int roomNumber) throws Exception{
        if(roomNumber <= 0){
            throw new Exception("Room number must be greater than 0.");
        }
        this.roomNumber = roomNumber;
    }

    @Override
    public void setWindows(int numberOfWindows) throws Exception{
        if(numberOfWindows < 0){
            throw new Exception("Number of windows cannot be less than 0.");
        }
        this.windows = numberOfWindows;
    }

    @Override
    public void setDoors(int numberOfDoors) throws Exception{
        if(numberOfDoors < 1){
            throw new Exception("There must be at least one door.");
        }
        this.doors = numberOfDoors;
    }

    @Override
    public void addSensor(Sensor sensor) {
        this.sensors.add(sensor);
    }

    @Override
    public Room getResult() {
        return new Room(roomType, roomNumber, area, windows, doors, sensors);
    }
}
