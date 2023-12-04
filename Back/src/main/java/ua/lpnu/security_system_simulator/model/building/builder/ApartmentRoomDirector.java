package ua.lpnu.security_system_simulator.model.building.builder;

import ua.lpnu.security_system_simulator.model.building.RoomType;
import ua.lpnu.security_system_simulator.model.sensor.FireSensor;
import ua.lpnu.security_system_simulator.model.sensor.FloodingSensor;
import ua.lpnu.security_system_simulator.model.sensor.GasLeakSensor;
import ua.lpnu.security_system_simulator.model.sensor.MotionSensor;

public class ApartmentRoomDirector {
    public int roomNumber;
    public void constructApartmentRoom(Builder builder) throws Exception{
        builder.setRoomType(RoomType.APARTMENT_ROOM);
        builder.setArea(26);
        builder.setWindows(1);
        builder.setDoors(1);
        //TODO room number
        builder.setRoomNumber(10);
        builder.addSensor(new FireSensor(20));
        builder.addSensor(new FloodingSensor(20));
        builder.addSensor(new GasLeakSensor(20));
        builder.addSensor(new MotionSensor(10));
    }
    public void constructApartmentBathroom(Builder builder)throws Exception{
        builder.setRoomType(RoomType.APARTMENT_BATHROOM);
        builder.setArea(15);
        builder.setDoors(1);
        //TODO room number
        builder.setRoomNumber(11);
        builder.addSensor(new FireSensor(20));
        builder.addSensor(new FloodingSensor(20));
        builder.addSensor(new GasLeakSensor(20));
        builder.addSensor(new MotionSensor(10));
    }
    public void constructApartmentKitchen(Builder builder) throws Exception{
        builder.setRoomType(RoomType.KITCHEN);
        builder.setArea(20);
        builder.setWindows(1);
        builder.setDoors(1);
        //TODO room number
        builder.setRoomNumber(12);
        builder.addSensor(new FireSensor(20));
        builder.addSensor(new FloodingSensor(20));
        builder.addSensor(new GasLeakSensor(20));
        builder.addSensor(new MotionSensor(10));
    }
}
