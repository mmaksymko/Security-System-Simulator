package ua.lpnu.security_system_simulator.model.building.builder;

import ua.lpnu.security_system_simulator.model.building.RoomType;
import ua.lpnu.security_system_simulator.model.sensor.FireSensor;
import ua.lpnu.security_system_simulator.model.sensor.FloodingSensor;
import ua.lpnu.security_system_simulator.model.sensor.GasLeakSensor;
import ua.lpnu.security_system_simulator.model.sensor.MotionSensor;

public class OfficeRoomDirector {
    public int roomNumber;
    public void constructBigOfficeRoom(Builder builder) throws Exception{
        builder.setRoomType(RoomType.OFFICE_ROOM);
        builder.setArea(60);
        builder.setWindows(6);
        builder.setDoors(2);
        //TODO room number
        builder.setRoomNumber(10);
        builder.addSensor(new FireSensor(20));
        builder.addSensor(new FloodingSensor(20));
        builder.addSensor(new GasLeakSensor(20));
        builder.addSensor(new MotionSensor(10));
    }
    public void constructSmallOfficeRoom(Builder builder) throws Exception{
        builder.setRoomType(RoomType.OFFICE_ROOM);
        builder.setArea(10);
        builder.setDoors(1);
        //TODO room number
        builder.setRoomNumber(11);
        builder.addSensor(new FireSensor(20));
        builder.addSensor(new FloodingSensor(20));
        builder.addSensor(new GasLeakSensor(20));
        builder.addSensor(new MotionSensor(10));
    }
    public void constructOfficeRestroom(Builder builder)throws Exception{
        builder.setRoomType(RoomType.OFFICE_RESTROOM);
        builder.setArea(30);
        builder.setDoors(1);
        //TODO room number
        builder.setRoomNumber(12);
        builder.addSensor(new FireSensor(20));
        builder.addSensor(new FloodingSensor(20));
        builder.addSensor(new GasLeakSensor(20));
        builder.addSensor(new MotionSensor(10));
    }
    public void constructOfficeKitchen(Builder builder) throws Exception{
        builder.setRoomType(RoomType.KITCHEN);
        builder.setArea(30);
        builder.setWindows(3);
        builder.setDoors(1);
        //TODO room number
        builder.setRoomNumber(13);
        builder.addSensor(new FireSensor(20));
        builder.addSensor(new FloodingSensor(20));
        builder.addSensor(new GasLeakSensor(20));
        builder.addSensor(new MotionSensor(10));
    }
}
