package ua.lpnu.security_system_simulator.model.building.roomBuilder;

import org.junit.jupiter.api.Test;
import ua.lpnu.security_system_simulator.model.building.Room;
import ua.lpnu.security_system_simulator.model.building.RoomType;
import ua.lpnu.security_system_simulator.model.building.builder.room.ApartmentRoomBuilder;
import ua.lpnu.security_system_simulator.model.building.builder.room.RoomDirector;
import ua.lpnu.security_system_simulator.model.sensor.*;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class ApartmentRoomDirectorTest {
    @Test
    public void constructApartmentRoom_CreatesCorrectRoom() throws Exception{
        LinkedList<Sensor> expectedSensors = new LinkedList<>();
        expectedSensors.add(new OpenedWindowSensor(2));
        expectedSensors.add(new OpenedDoorSensor(2));
        expectedSensors.add(new FireSensor(20));
        expectedSensors.add(new FloodingSensor(20));
        expectedSensors.add(new GasLeakSensor(20));
        expectedSensors.add(new MotionSensor(10));
        expectedSensors.add(new MotionSensor(10));
        expectedSensors.add(new MotionSensor(10));
        Room expected = new Room(RoomType.APARTMENT_ROOM, 10, 26, 1, 1, expectedSensors, new ArrayList<>());
        RoomDirector director = new RoomDirector();
        ApartmentRoomBuilder builder = new ApartmentRoomBuilder();
        director.constructApartmentRoom(builder);
        Room actual = builder.getResult();
        assertEquals(expected, actual);

    }
    @Test
    public void constructApartmentBathroom_CreatesCorrectRoom() throws Exception{
        LinkedList<Sensor> expectedSensors = new LinkedList<>();
        expectedSensors.add(new OpenedDoorSensor(2));
        expectedSensors.add(new FireSensor(20));
        expectedSensors.add(new FloodingSensor(20));
        expectedSensors.add(new GasLeakSensor(20));
        expectedSensors.add(new MotionSensor(10));
        expectedSensors.add(new MotionSensor(10));
        Room expected = new Room(RoomType.APARTMENT_BATHROOM, 11, 15, 0, 1, expectedSensors, new ArrayList<>());
        RoomDirector director = new RoomDirector();
        ApartmentRoomBuilder builder = new ApartmentRoomBuilder();
        director.constructApartmentBathroom(builder);
        Room actual = builder.getResult();
        assertEquals(expected, actual);
    }
    @Test
    public void constructApartmentKitchen_CreatesCorrectRoom() throws Exception{
        LinkedList<Sensor> expectedSensors = new LinkedList<>();
        expectedSensors.add(new OpenedWindowSensor(2));
        expectedSensors.add(new OpenedDoorSensor(2));
        expectedSensors.add(new FireSensor(20));
        expectedSensors.add(new FloodingSensor(20));
        expectedSensors.add(new GasLeakSensor(20));
        expectedSensors.add(new MotionSensor(10));
        expectedSensors.add(new MotionSensor(10));
        Room expected = new Room(RoomType.KITCHEN, 12, 20, 1, 1, expectedSensors, new ArrayList<>());
        RoomDirector director = new RoomDirector();
        ApartmentRoomBuilder builder = new ApartmentRoomBuilder();
        director.constructApartmentKitchen(builder);
        Room actual = builder.getResult();
        assertEquals(expected, actual);
    }

}