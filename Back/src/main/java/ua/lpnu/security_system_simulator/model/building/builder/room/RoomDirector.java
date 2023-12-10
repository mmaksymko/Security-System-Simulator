package ua.lpnu.security_system_simulator.model.building.builder.room;

import ua.lpnu.security_system_simulator.model.building.RoomType;
import ua.lpnu.security_system_simulator.model.sensor.FireSensor;
import ua.lpnu.security_system_simulator.model.sensor.FloodingSensor;
import ua.lpnu.security_system_simulator.model.sensor.GasLeakSensor;
import ua.lpnu.security_system_simulator.model.sensor.MotionSensor;

public class RoomDirector {
    public int roomNumberCounter;
    public RoomDirector(int floor){
        this.roomNumberCounter = floor * 100;
    }
    public void constructApartmentRoom(RoomBuilder builder) throws Exception{
        builder.setRoomType(RoomType.APARTMENT_ROOM);
        builder.setWidth(2);
        builder.setLength(2);
        builder.setWindows(2);
        builder.setDoors(1);
        builder.setRoomNumber(roomNumberCounter++);
        builder.addSensor(new FireSensor(2));
        builder.addSensor(new FloodingSensor(2));
        builder.addSensor(new GasLeakSensor(2));
        builder.addSensor(new MotionSensor(1));
    }
    public void constructApartmentBathroom(RoomBuilder builder)throws Exception{
        builder.setRoomType(RoomType.APARTMENT_BATHROOM);
        builder.setWidth(1);
        builder.setLength(1);
        builder.setDoors(1);
        builder.setRoomNumber(roomNumberCounter++);
        builder.addSensor(new FireSensor(2));
        builder.addSensor(new FloodingSensor(2));
        builder.addSensor(new GasLeakSensor(2));
        builder.addSensor(new MotionSensor(1));
    }
    public void constructApartmentKitchen(RoomBuilder builder) throws Exception{
        builder.setRoomType(RoomType.KITCHEN);
        builder.setWidth(3);
        builder.setLength(1);
        builder.setWindows(1);
        builder.setDoors(1);
        builder.setRoomNumber(roomNumberCounter++);
        builder.addSensor(new FireSensor(2));
        builder.addSensor(new FloodingSensor(2));
        builder.addSensor(new GasLeakSensor(2));
        builder.addSensor(new MotionSensor(1));
    }
    public void constructBigOfficeRoom(RoomBuilder builder) throws Exception{
        builder.setRoomType(RoomType.OFFICE_ROOM);
        builder.setWidth(3);
        builder.setLength(2);
        builder.setWindows(5);
        builder.setDoors(2);
        builder.setRoomNumber(roomNumberCounter++);
        builder.addSensor(new FireSensor(2));
        builder.addSensor(new FloodingSensor(2));
        builder.addSensor(new GasLeakSensor(2));
        builder.addSensor(new MotionSensor(1));
    }
    public void constructSmallOfficeRoom(RoomBuilder builder) throws Exception{
        builder.setRoomType(RoomType.OFFICE_ROOM);
        builder.setWidth(1);
        builder.setLength(1);
        builder.setDoors(1);
        builder.setRoomNumber(roomNumberCounter++);
        builder.addSensor(new FireSensor(2));
        builder.addSensor(new FloodingSensor(2));
        builder.addSensor(new GasLeakSensor(2));
        builder.addSensor(new MotionSensor(1));
    }
    public void constructOfficeRestroom(RoomBuilder builder)throws Exception{
        builder.setRoomType(RoomType.OFFICE_RESTROOM);
        builder.setWidth(2);
        builder.setLength(1);
        builder.setDoors(1);
        builder.setRoomNumber(roomNumberCounter++);
        builder.addSensor(new FireSensor(2));
        builder.addSensor(new FloodingSensor(2));
        builder.addSensor(new GasLeakSensor(2));
        builder.addSensor(new MotionSensor(1));
    }
    public void constructOfficeKitchen(RoomBuilder builder) throws Exception{
        builder.setRoomType(RoomType.KITCHEN);
        builder.setWidth(1);
        builder.setLength(2);
        builder.setWindows(2);
        builder.setDoors(1);
        builder.setRoomNumber(roomNumberCounter++);
        builder.addSensor(new FireSensor(2));
        builder.addSensor(new FloodingSensor(2));
        builder.addSensor(new GasLeakSensor(2));
        builder.addSensor(new MotionSensor(1));
    }
}
