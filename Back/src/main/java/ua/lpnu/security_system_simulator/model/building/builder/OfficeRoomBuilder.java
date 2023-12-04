package ua.lpnu.security_system_simulator.model.building.builder;

import ua.lpnu.security_system_simulator.model.building.Room;
import ua.lpnu.security_system_simulator.model.building.RoomType;
import ua.lpnu.security_system_simulator.model.sensor.OpenedWindowSensor;
import ua.lpnu.security_system_simulator.model.sensor.Sensor;

import java.util.LinkedList;
import java.util.List;

public class OfficeRoomBuilder implements Builder{
    private RoomType roomType;
    private int roomNumber;
    private int area;
    private int windows;
    private int doors;
    private List<Sensor> sensors;
    OfficeRoomBuilder(){
        sensors = new LinkedList<>();
    }

    @Override
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public void setArea(int area) throws Exception{
        if(area <= 0){
            throw new IllegalArgumentException("Area must be greater than 0.");
        }
        this.area = area;
    }

    @Override
    public void setRoomNumber(int roomNumber) throws Exception{
        if(roomNumber <= 0){
            throw new IllegalArgumentException("Room number must be greater than 0.");
        }
        this.roomNumber = roomNumber;
    }

    @Override
    public void setWindows(int numberOfWindows) throws Exception{
        int minWindowCount = area / 10;
        int maxWindowCount = area / 6;
        if(area == 0){
            throw new NullPointerException("An area must be set first");
        }
        if(numberOfWindows < minWindowCount || numberOfWindows > maxWindowCount){
            throw new IllegalArgumentException("Number of windows must be in bounds of ["
                    + minWindowCount + "; "
                    + maxWindowCount + "].");
        }
        this.windows = numberOfWindows;
        for(int i = 0; i < numberOfWindows; ++i){
            sensors.add(new OpenedWindowSensor(5));
        }
    }

    @Override
    public void setDoors(int numberOfDoors) throws Exception{
        if(numberOfDoors < 1){
            throw new IllegalArgumentException("There must be at least one door.");
        }
        if(area == 0){
            throw new NullPointerException("An area must be set first");
        }
        this.doors = numberOfDoors;
        for(int i = 0; i < numberOfDoors; ++i){
            sensors.add(new OpenedWindowSensor(5));
        }
    }

    @Override
    public void addSensor(Sensor sensor) {
        this.sensors.add(sensor);
    }

    @Override
    public Room getResult() throws Exception{
        if(doors <= 0){
            throw new Exception("Cannot get room without a door");
        }
        return new Room(roomType, roomNumber, area, windows, doors, sensors);
    }
}
