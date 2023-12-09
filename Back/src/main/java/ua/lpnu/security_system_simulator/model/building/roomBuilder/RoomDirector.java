package ua.lpnu.security_system_simulator.model.building.roomBuilder;

import ua.lpnu.security_system_simulator.model.building.RoomType;
import ua.lpnu.security_system_simulator.model.sensor.FireSensor;
import ua.lpnu.security_system_simulator.model.sensor.FloodingSensor;
import ua.lpnu.security_system_simulator.model.sensor.GasLeakSensor;
import ua.lpnu.security_system_simulator.model.sensor.MotionSensor;

public class RoomDirector {
    public int roomNumber;
    public void constructBigApartmentRoom(Builder builder) throws Exception{
        builder.setRoomType(RoomType.APARTMENT_ROOM);
        builder.setWidth(2);
        builder.setLength(2);
        builder.setWindows(2);
        builder.setDoors(1);
        //TODO room number
        builder.setRoomNumber(10);
        builder.addSensor(new FireSensor(2));
        builder.addSensor(new FloodingSensor(2));
        builder.addSensor(new GasLeakSensor(2));
        builder.addSensor(new MotionSensor(1));
    }
    public void constructSmallApartmentRoom(Builder builder) throws Exception{
        builder.setRoomType(RoomType.APARTMENT_ROOM);
        builder.setWidth(1);
        builder.setLength(2);
        builder.setWindows(1);
        builder.setDoors(1);
        //TODO room number
        builder.setRoomNumber(10);
        builder.addSensor(new FireSensor(2));
        builder.addSensor(new FloodingSensor(2));
        builder.addSensor(new GasLeakSensor(2));
        builder.addSensor(new MotionSensor(1));
    }
    public void constructApartmentBathroom(Builder builder)throws Exception{
        builder.setRoomType(RoomType.APARTMENT_BATHROOM);
        builder.setWidth(1);
        builder.setLength(1);
        builder.setDoors(1);
        //TODO room number
        builder.setRoomNumber(11);
        builder.addSensor(new FireSensor(2));
        builder.addSensor(new FloodingSensor(2));
        builder.addSensor(new GasLeakSensor(2));
        builder.addSensor(new MotionSensor(1));
    }
    public void constructApartmentKitchen(Builder builder) throws Exception{
        builder.setRoomType(RoomType.KITCHEN);
        builder.setWidth(2);
        builder.setLength(1);
        builder.setWindows(1);
        builder.setDoors(1);
        //TODO room number
        builder.setRoomNumber(12);
        builder.addSensor(new FireSensor(2));
        builder.addSensor(new FloodingSensor(2));
        builder.addSensor(new GasLeakSensor(2));
        builder.addSensor(new MotionSensor(1));
    }
    public void constructBigOfficeRoom(Builder builder) throws Exception{
        builder.setRoomType(RoomType.OFFICE_ROOM);
        builder.setWidth(8);
        builder.setLength(8);
        builder.setWindows(6);
        builder.setDoors(2);
        //TODO room number
        builder.setRoomNumber(10);
        builder.addSensor(new FireSensor(2));
        builder.addSensor(new FloodingSensor(2));
        builder.addSensor(new GasLeakSensor(2));
        builder.addSensor(new MotionSensor(1));
    }
    public void constructSmallOfficeRoom(Builder builder) throws Exception{
        builder.setRoomType(RoomType.OFFICE_ROOM);
        builder.setWidth(3);
        builder.setLength(3);
        builder.setDoors(1);
        //TODO room number
        builder.setRoomNumber(11);
        builder.addSensor(new FireSensor(2));
        builder.addSensor(new FloodingSensor(2));
        builder.addSensor(new GasLeakSensor(2));
        builder.addSensor(new MotionSensor(1));
    }
    public void constructOfficeRestroom(Builder builder)throws Exception{
        builder.setRoomType(RoomType.OFFICE_RESTROOM);
        builder.setWidth(2);
        builder.setLength(2);
        builder.setDoors(1);
        //TODO room number
        builder.setRoomNumber(12);
        builder.addSensor(new FireSensor(2));
        builder.addSensor(new FloodingSensor(2));
        builder.addSensor(new GasLeakSensor(2));
        builder.addSensor(new MotionSensor(1));
    }
    public void constructOfficeKitchen(Builder builder) throws Exception{
        builder.setRoomType(RoomType.KITCHEN);
        builder.setWidth(4);
        builder.setLength(4);
        builder.setWindows(3);
        builder.setDoors(1);
        //TODO room number
        builder.setRoomNumber(13);
        builder.addSensor(new FireSensor(2));
        builder.addSensor(new FloodingSensor(2));
        builder.addSensor(new GasLeakSensor(2));
        builder.addSensor(new MotionSensor(1));
    }
}
