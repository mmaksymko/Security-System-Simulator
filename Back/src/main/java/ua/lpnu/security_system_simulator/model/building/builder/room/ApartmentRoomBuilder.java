package ua.lpnu.security_system_simulator.model.building.builder.room;

import ua.lpnu.security_system_simulator.model.building.Room;
import ua.lpnu.security_system_simulator.model.building.RoomType;
import ua.lpnu.security_system_simulator.model.sensor.OpenedDoorSensor;
import ua.lpnu.security_system_simulator.model.sensor.OpenedWindowSensor;
import ua.lpnu.security_system_simulator.model.sensor.Sensor;

import java.util.ArrayList;
import java.util.List;

public class ApartmentRoomBuilder implements Builder{
    private RoomType roomType;
    private int roomNumber;
    private int width;
    private int length;
    private int windows;
    private int doors;
    private List<Sensor> sensors;
    ApartmentRoomBuilder(){
        sensors = new ArrayList<>();
    }

    @Override
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public void setWidth(int width) throws IllegalArgumentException{
        if(width <= 0){
            throw new IllegalArgumentException("The area must be greater than 0.");
        }
        this.width = width;
    }

    @Override
    public void setLength(int length) throws IllegalArgumentException{
        if(length <= 0){
            throw new IllegalArgumentException("The area must be greater than 0.");
        }
        this.length = length;
    }

    @Override
    public void setRoomNumber(int roomNumber) throws IllegalArgumentException{
        if(roomNumber <= 0){
            throw new IllegalArgumentException("Room number must be greater than 0.");
        }
        this.roomNumber = roomNumber;
    }

    @Override
    public void setWindows(int numberOfWindows) throws IllegalArgumentException{
        float floatArea = Integer.valueOf(getArea()).floatValue();
        int minWindowCount = Float.valueOf(floatArea / 3).intValue();
        int maxWindowCount = Float.valueOf(floatArea / 2).intValue();
        if(getArea() == 0){
            throw new IllegalArgumentException("An area must be set first");
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
        if(numberOfDoors < 1){
            throw new IllegalArgumentException("There must be at least one door.");
        }
        if(getArea() == 0){
            throw new IllegalArgumentException("An area must be set first");
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
        if(doors <= 0){
            throw new IllegalArgumentException("Cannot get room without a door");
        }
        return new Room(roomType, roomNumber, width, length, windows, doors, sensors, new ArrayList<>());
    }
}
