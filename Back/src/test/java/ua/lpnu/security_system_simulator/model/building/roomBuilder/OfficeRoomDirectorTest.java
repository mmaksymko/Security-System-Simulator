package ua.lpnu.security_system_simulator.model.building.roomBuilder;

import org.junit.jupiter.api.Test;
import ua.lpnu.security_system_simulator.model.building.Room;
import ua.lpnu.security_system_simulator.model.building.RoomType;
import ua.lpnu.security_system_simulator.model.building.builder.room.OfficeRoomBuilder;
import ua.lpnu.security_system_simulator.model.building.builder.room.RoomDirector;
import ua.lpnu.security_system_simulator.model.sensor.*;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class OfficeRoomDirectorTest {
    @Test
    public void constructBigOfficeRoom_CreatesCorrectRoom() throws Exception{
        LinkedList<Sensor> expectedSensors = new LinkedList<>();
        expectedSensors.add(new OpenedWindowSensor(5));
        expectedSensors.add(new OpenedWindowSensor(5));
        expectedSensors.add(new OpenedWindowSensor(5));
        expectedSensors.add(new OpenedWindowSensor(5));
        expectedSensors.add(new OpenedWindowSensor(5));
        expectedSensors.add(new OpenedWindowSensor(5));
        expectedSensors.add(new OpenedDoorSensor(5));
        expectedSensors.add(new OpenedDoorSensor(5));
        expectedSensors.add(new FireSensor(20));
        expectedSensors.add(new FireSensor(20));
        expectedSensors.add(new FireSensor(20));
        expectedSensors.add(new FloodingSensor(20));
        expectedSensors.add(new FloodingSensor(20));
        expectedSensors.add(new FloodingSensor(20));
        expectedSensors.add(new GasLeakSensor(20));
        expectedSensors.add(new GasLeakSensor(20));
        expectedSensors.add(new GasLeakSensor(20));
        expectedSensors.add(new MotionSensor(10));
        expectedSensors.add(new MotionSensor(10));
        expectedSensors.add(new MotionSensor(10));
        expectedSensors.add(new MotionSensor(10));
        expectedSensors.add(new MotionSensor(10));
        expectedSensors.add(new MotionSensor(10));
        Room expected = new Room(RoomType.OFFICE_ROOM, 10, 60, 6, 2, expectedSensors, new ArrayList<>());
        RoomDirector director = new RoomDirector();
        OfficeRoomBuilder builder = new OfficeRoomBuilder();
        director.constructBigOfficeRoom(builder);
        Room actual = builder.getResult();
        assertEquals(expected, actual);
    }
    @Test
    public void constructSmallOfficeRoom_CreatesCorrectRoom() throws Exception{
        LinkedList<Sensor> expectedSensors = new LinkedList<>();
        expectedSensors.add(new OpenedDoorSensor(5));
        expectedSensors.add(new FireSensor(20));
        expectedSensors.add(new FloodingSensor(20));
        expectedSensors.add(new GasLeakSensor(20));
        expectedSensors.add(new MotionSensor(10));
        Room expected = new Room(RoomType.OFFICE_ROOM, 11, 10, 0, 1, expectedSensors, new ArrayList<>());
        RoomDirector director = new RoomDirector();
        OfficeRoomBuilder builder = new OfficeRoomBuilder();
        director.constructSmallOfficeRoom(builder);
        Room actual = builder.getResult();
        assertEquals(expected, actual);
    }
    @Test
    public void constructOfficeRestroom_CreatesCorrectRoom() throws Exception{
        LinkedList<Sensor> expectedSensors = new LinkedList<>();
        expectedSensors.add(new OpenedDoorSensor(5));
        expectedSensors.add(new FireSensor(20));
        expectedSensors.add(new FireSensor(20));
        expectedSensors.add(new FloodingSensor(20));
        expectedSensors.add(new FloodingSensor(20));
        expectedSensors.add(new GasLeakSensor(20));
        expectedSensors.add(new GasLeakSensor(20));
        expectedSensors.add(new MotionSensor(10));
        expectedSensors.add(new MotionSensor(10));
        expectedSensors.add(new MotionSensor(10));
        Room expected = new Room(RoomType.OFFICE_RESTROOM, 12, 30, 0, 1, expectedSensors, new ArrayList<>());
        RoomDirector director = new RoomDirector();
        OfficeRoomBuilder builder = new OfficeRoomBuilder();
        director.constructOfficeRestroom(builder);
        Room actual = builder.getResult();
        assertEquals(expected, actual);
    }
    @Test
    public void constructOfficeKitchen_CreatesCorrectRoom() throws Exception{
        LinkedList<Sensor> expectedSensors = new LinkedList<>();
        expectedSensors.add(new OpenedWindowSensor(5));
        expectedSensors.add(new OpenedWindowSensor(5));
        expectedSensors.add(new OpenedWindowSensor(5));
        expectedSensors.add(new OpenedDoorSensor(5));
        expectedSensors.add(new FireSensor(20));
        expectedSensors.add(new FireSensor(20));
        expectedSensors.add(new FloodingSensor(20));
        expectedSensors.add(new FloodingSensor(20));
        expectedSensors.add(new GasLeakSensor(20));
        expectedSensors.add(new GasLeakSensor(20));
        expectedSensors.add(new MotionSensor(10));
        expectedSensors.add(new MotionSensor(10));
        expectedSensors.add(new MotionSensor(10));
        Room expected = new Room(RoomType.KITCHEN, 13, 30, 3, 1, expectedSensors, new ArrayList<>());
        RoomDirector director = new RoomDirector();
        OfficeRoomBuilder builder = new OfficeRoomBuilder();
        director.constructOfficeKitchen(builder);
        Room actual = builder.getResult();
        assertEquals(expected, actual);
    }
}