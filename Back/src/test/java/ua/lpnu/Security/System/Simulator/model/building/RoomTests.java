package ua.lpnu.Security.System.Simulator.model.building;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.lpnu.security_system_simulator.model.building.Room;
import ua.lpnu.security_system_simulator.model.building.RoomType;
import ua.lpnu.security_system_simulator.model.event.EventType;
import ua.lpnu.security_system_simulator.model.sensor.*;

import java.util.ArrayList;
import java.util.List;

public class RoomTests {
    @Test
    public void constructionWithInvalidParameters_ThrowsIllegalArgumentException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Room(RoomType.APARTMENT_ROOM,-1,1,1,1, new ArrayList<>(), new ArrayList<>()));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Room(RoomType.APARTMENT_ROOM,0,0,0,0, new ArrayList<>(), new ArrayList<>()));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Room(RoomType.OFFICE_ROOM,1,1,-1,1,new ArrayList<>(), new ArrayList<>()));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Room(RoomType.OFFICE_ROOM,1,1,1,-1,new ArrayList<>(), new ArrayList<>()));
    }

    @Test
    public void constructionWithValidParameters_ReturnsRoom(){
        Room room1 = new Room(RoomType.OFFICE_ROOM,0,1,0,0,new ArrayList<>(), new ArrayList<>());
        Room room2 = new Room(RoomType.OFFICE_ROOM,10,10,10,10, new ArrayList<>(),  new ArrayList<>());
        Room room3 = new Room(RoomType.APARTMENT_ROOM,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE, new ArrayList<>(), new ArrayList<>());
        Room room4 = new Room(RoomType.APARTMENT_ROOM,Integer.MAX_VALUE-1,Integer.MAX_VALUE-1,Integer.MAX_VALUE-1,Integer.MAX_VALUE-1, new ArrayList<>(), new ArrayList<>());

        Assertions.assertEquals(0, room1.getRoomNumber());
        Assertions.assertEquals(1, room1.getArea());
        Assertions.assertEquals(0, room1.getWindows());
        Assertions.assertEquals(0, room1.getDoors());

        Assertions.assertEquals(10, room2.getRoomNumber());
        Assertions.assertEquals(10, room2.getArea());
        Assertions.assertEquals(10, room2.getWindows());
        Assertions.assertEquals(10, room2.getDoors());

        Assertions.assertEquals(Integer.MAX_VALUE, room3.getRoomNumber());
        Assertions.assertEquals(Integer.MAX_VALUE, room3.getArea());
        Assertions.assertEquals(Integer.MAX_VALUE, room3.getWindows());
        Assertions.assertEquals(Integer.MAX_VALUE, room3.getDoors());

        Assertions.assertEquals(Integer.MAX_VALUE-1, room4.getRoomNumber());
        Assertions.assertEquals(Integer.MAX_VALUE-1, room4.getArea());
        Assertions.assertEquals(Integer.MAX_VALUE-1, room4.getWindows());
        Assertions.assertEquals(Integer.MAX_VALUE-1, room4.getDoors());
    }

    @Test
    public void addComponent_ThrowsUnsupportedOperationException(){
        Room room = new Room(RoomType.APARTMENT_ROOM,10,10,10,10,new ArrayList<>(), new ArrayList<>());
        Assertions.assertThrows(UnsupportedOperationException.class, () -> room.addComponent(new Room(RoomType.APARTMENT_ROOM,1,10,10,10, new ArrayList<>(), new ArrayList<>())));
    }

    @Test
    public void removeComponent_ThrowsUnsupportedOperationException(){
        Room room = new Room(RoomType.OFFICE_RESTROOM,10,10,10,10, new ArrayList<>(), new ArrayList<>());
        Assertions.assertThrows(UnsupportedOperationException.class, () -> room.removeComponent(new Room(RoomType.OFFICE_ROOM,1,10,10,10, new ArrayList<>(), new ArrayList<>())));
    }

    @Test
    public void getComponents_ReturnsEmptyList(){
        Room room = new Room(RoomType.APARTMENT_ROOM,10,10,10,10, new ArrayList<>(), new ArrayList<>());
        Assertions.assertEquals(new ArrayList<>(), room.getComponents());
    }

    @Test
    public void getNumberOfComponents_Returns1(){
        Room room = new Room(RoomType.APARTMENT_ROOM,10,10,10,10, new ArrayList<>(), new ArrayList<>());
        Assertions.assertEquals(1, room.getNumberOfComponents());
    }

    @Test
    public void addingNotOpenableSensorForRoomWithNoOpenables_DoesNotThrow(){
        Room room = new Room(RoomType.OFFICE_ROOM,10,10,0,0, new ArrayList<>(), new ArrayList<>());
        Assertions.assertDoesNotThrow(() -> room.addSensor(new FireSensor(15)));
        Assertions.assertDoesNotThrow(() -> room.addSensor(new FloodingSensor(15)));
        Assertions.assertDoesNotThrow(() -> room.addSensor(new FloodingSensor(15)));
        Assertions.assertDoesNotThrow(() -> room.addSensor(new GasLeakSensor(15)));
        Assertions.assertDoesNotThrow(() -> room.addSensor(new FireSensor(15)));
        Assertions.assertDoesNotThrow(() -> room.addSensor(new FloodingSensor(15)));
        Assertions.assertDoesNotThrow(() -> room.addSensor(new FireSensor(15)));
        Assertions.assertDoesNotThrow(() -> room.addSensor(new FireSensor(15)));
        Assertions.assertDoesNotThrow(() -> room.addSensor(new MotionSensor(15)));
    }

    @Test
    public void addingNotOpenableSensorForRoomWithMaximumOpenables_DoesNotThrow(){
        Room room = new Room(RoomType.OFFICE_ROOM,10,10,2,2, new ArrayList<>(), new ArrayList<>());
        room.addSensor(new OpenedDoorSensor(15));
        room.addSensor(new OpenedDoorSensor(15));
        room.addSensor(new OpenedWindowSensor(10));
        room.addSensor(new OpenedWindowSensor(10));

        Assertions.assertDoesNotThrow(() -> room.addSensor(new FireSensor(15)));
        Assertions.assertDoesNotThrow(() -> room.addSensor(new FloodingSensor(15)));
        Assertions.assertDoesNotThrow(() -> room.addSensor(new FloodingSensor(15)));
        Assertions.assertDoesNotThrow(() -> room.addSensor(new GasLeakSensor(15)));
        Assertions.assertDoesNotThrow(() -> room.addSensor(new FireSensor(15)));
        Assertions.assertDoesNotThrow(() -> room.addSensor(new FloodingSensor(15)));
        Assertions.assertDoesNotThrow(() -> room.addSensor(new FireSensor(15)));
        Assertions.assertDoesNotThrow(() -> room.addSensor(new FireSensor(15)));
        Assertions.assertDoesNotThrow(() -> room.addSensor(new MotionSensor(15)));
    }

    @Test
    public void addingOpenableSensorForRoomWithNoOpenables_ThrowsIllegalArgumentException(){
        Room room = new Room(RoomType.KITCHEN,10,10,0,0, new ArrayList<>(), new ArrayList<>());
        Assertions.assertThrows(IllegalArgumentException.class, () -> room.addSensor(new OpenedDoorSensor(15)));
        Assertions.assertThrows(IllegalArgumentException.class, () -> room.addSensor(new OpenedWindowSensor(10)));
    }

    @Test
    public void addingDoorSensorForRoomWithNoDoorsAndEnoughWindows_ThrowsIllegalArgumentException(){
        Room room = new Room(RoomType.APARTMENT_ROOM,10,10,10,0, new ArrayList<>(), new ArrayList<>());
        Assertions.assertThrows(IllegalArgumentException.class, () -> room.addSensor(new OpenedDoorSensor(15)));
    }

    @Test
    public void addingWindowSensorsForRoomWithNoDoorsAndNotMaximumWindows_DoesNotThrow(){
        Room room = new Room(RoomType.APARTMENT_ROOM,10,10,10,0, new ArrayList<>(), new ArrayList<>());
        for (int i = 0; i!=10; ++i){
            Assertions.assertDoesNotThrow(() -> room.addSensor(new OpenedWindowSensor(15)));
        }
    }

    @Test
    public void addingWindowSensorForRoomWithNoDoorsAndMaximumWindows_ThrowsIllegalArgumentException(){
        Room room = new Room(RoomType.OFFICE_ROOM,10,10,10,0,new ArrayList<>(), new ArrayList<>());
        for (int i = 0; i!=10; ++i){
            room.addSensor(new OpenedWindowSensor(15));
        }
        Assertions.assertThrows(IllegalArgumentException.class, () -> room.addSensor(new OpenedWindowSensor(15)));
    }

    @Test
    public void addingWindowSensorForRoomWithNoWindowsAndEnoughDoors_ThrowsIllegalArgumentException(){
        Room room = new Room(RoomType.OFFICE_ROOM,10,10,0,10, new ArrayList<>(), new ArrayList<>());
        Assertions.assertThrows(IllegalArgumentException.class, () -> room.addSensor(new OpenedWindowSensor(15)));
    }

    @Test
    public void addingDoorSensorsForRoomWithNoWindowsAndNotMaximumDoors_DoesNotThrow(){
        Room room = new Room(RoomType.APARTMENT_ROOM,10,10,0,10,new ArrayList<>(), new ArrayList<>());
        for (int i = 0; i!=10; ++i){
            Assertions.assertDoesNotThrow(() -> room.addSensor(new OpenedDoorSensor(15)));
        }
    }

    @Test
    public void addingDoorSensorForRoomWithNoWindowsAndMaximumDoors_ThrowsIllegalArgumentException(){
        Room room = new Room(RoomType.APARTMENT_ROOM,10,10,0,10,new ArrayList<>(), new ArrayList<>());
        for (int i = 0; i!=10; ++i){
            room.addSensor(new OpenedDoorSensor(15));
        }
        Assertions.assertThrows(IllegalArgumentException.class, () -> room.addSensor(new OpenedDoorSensor(15)));
    }

    @Test
    public void addSensor_AddsSensor(){
        Room room = new Room(RoomType.OFFICE_ROOM,10,10,10,10,new ArrayList<>(), new ArrayList<>());

        room.addSensor(new FireSensor(10));
        room.addSensor(new FireSensor(20));
        room.addSensor(new FireSensor(40));
        room.addSensor(new FloodingSensor(40));

        Assertions.assertEquals(room.sensorCount(), 4);
    }

    @Test
    public void removeSensor_RemovesSensor(){
        Room room = new Room(RoomType.OFFICE_ROOM,10,10,10,10,new ArrayList<>(), new ArrayList<>());

        var sensor1 = new FireSensor(10);
        var sensor2 = new FireSensor(20);
        var sensor3 = new FireSensor(40);
        var sensor4 = new FloodingSensor(40);
        room.addSensor(sensor1);
        room.addSensor(sensor2);
        room.addSensor(sensor3);
        room.addSensor(sensor4);

        room.removeSensor(sensor2);

        Assertions.assertEquals(room.sensorCount(), 3);
        Assertions.assertEquals(room.getAllSensorsOfType(EventType.FIRE), List.of(sensor1, sensor3));
        Assertions.assertEquals(room.getAllSensorsOfType(EventType.FLOODING), List.of(sensor4));
    }

    @Test
    public void getAllSensorsOfType_ReturnsListOfAllSensorsOfType() {
        Room room = new Room(RoomType.APARTMENT_ROOM,10,10,10,10,new ArrayList<>(), new ArrayList<>());

        room.addSensor(new FireSensor(10));
        room.addSensor(new FireSensor(20));
        room.addSensor(new FireSensor(40));
        room.addSensor(new FloodingSensor(40));
        room.addSensor(new OpenedDoorSensor(40));
        room.addSensor(new OpenedDoorSensor(40));
        room.addSensor(new FloodingSensor(40));
        room.addSensor(new FloodingSensor(40));
        room.addSensor(new GasLeakSensor(40));

        Assertions.assertEquals(room.sensorCount(), 9);
        Assertions.assertEquals(room.getAllSensorsOfType(EventType.MOTION).size(), 0);
        Assertions.assertEquals(room.getAllSensorsOfType(EventType.FIRE).size(), 3);
        Assertions.assertEquals(room.getAllSensorsOfType(EventType.FLOODING).size(), 3);
        Assertions.assertEquals(room.getAllSensorsOfType(EventType.OPENED_DOOR).size(), 2);
        Assertions.assertEquals(room.getAllSensorsOfType(EventType.OPENED_WINDOW).size(), 0);
        Assertions.assertEquals(room.getAllSensorsOfType(EventType.GAS_LEAK).size(), 1);
    }
}
