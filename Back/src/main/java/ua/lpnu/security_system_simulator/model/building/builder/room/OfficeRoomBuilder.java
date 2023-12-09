package ua.lpnu.security_system_simulator.model.building.builder.room;

import ua.lpnu.security_system_simulator.model.building.Room;
import ua.lpnu.security_system_simulator.model.building.RoomType;
import ua.lpnu.security_system_simulator.model.sensor.OpenedDoorSensor;
import ua.lpnu.security_system_simulator.model.sensor.OpenedWindowSensor;
import ua.lpnu.security_system_simulator.model.sensor.Sensor;

import java.util.ArrayList;
import java.util.List;

public class OfficeRoomBuilder implements Builder{
    private RoomType roomType;
    private int roomNumber;
    private int width;
    private int length;
    private int windows;
    private int doors;
    private List<Sensor> sensors;
    OfficeRoomBuilder(){
        sensors = new ArrayList<>();
    }

    @Override
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public void setWidth(int width){
        this.width = width;
    }
    @Override
    public void setLength(int length){
        this.length = length;
    }

    @Override
    public void setRoomNumber(int roomNumber){
        if(roomNumber <= 0){
            throw new IllegalArgumentException("Room number must be greater than 0.");
        }
        this.roomNumber = roomNumber;
    }

    @Override
    public void setWindows(int numberOfWindows) throws IllegalArgumentException{
        float floatArea = Integer.valueOf(getArea()).floatValue();
        int minWindowCount = Float.valueOf(floatArea / 2).intValue();
        int maxWindowCount = getArea();
        if(getArea() == 0){
            throw new IllegalArgumentException("The area must be set first");
        }
        if(numberOfWindows < minWindowCount || numberOfWindows > maxWindowCount){
            throw new IllegalArgumentException("Number of windows must be in bounds of ["
                    + minWindowCount + "; "
                    + maxWindowCount + "].");
        }
        this.windows = numberOfWindows;
        for(int i = 0; i < numberOfWindows; ++i){
            sensors.add(new OpenedWindowSensor(1));
        }
    }

    @Override
    public void setDoors(int numberOfDoors) throws IllegalArgumentException{
        if(getArea() == 0){
            throw new NullPointerException("The area must be set first");
        }
        this.doors = numberOfDoors;
        for(int i = 0; i < numberOfDoors; ++i){
            sensors.add(new OpenedDoorSensor(1));
        }
    }

    @Override
    public void addSensor(Sensor sensor) {
        int totalNumberOfSensors = Math.round((float) getArea() / sensor.getCoverageArea());
        if(totalNumberOfSensors == 0){
            totalNumberOfSensors = 1;
        }
        for(int i = 0; i < totalNumberOfSensors; ++i){
            this.sensors.add(sensor);
        }
    }

    @Override
    public int getArea(){
        return width * length;
    }

    @Override
    public Room build() throws IllegalArgumentException{
        return new Room(roomType, roomNumber, width, length, windows, doors, sensors, new ArrayList<>());
    }
}
