package ua.lpnu.Security.System.Simulator.model.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.lpnu.security_system_simulator.model.building.Room;
import ua.lpnu.security_system_simulator.model.building.RoomType;
import ua.lpnu.security_system_simulator.model.event.DangerLevel;
import ua.lpnu.security_system_simulator.model.event.*;
import ua.lpnu.security_system_simulator.model.sensor.Sensor;

import java.util.ArrayList;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ua.lpnu.security_system_simulator.model.event.EventFactory.class)
public class EventFactoryTests {
    @Autowired
    private EventFactory eventFactory;

    @Test
    public void whenRoomIsNull_ReturnsNull(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> eventFactory.createEvent(EventType.FIRE, null, DangerLevel.HIGH));
    }

    @Test
    public void whenEventTypeIsNull_ReturnsNull(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> eventFactory.createEvent(null, new Room(RoomType.OFFICE_ROOM,10,10,10,10,new ArrayList<Sensor>()), DangerLevel.HIGH));
    }

    @Test
    public void whenDangerLevelIsNull_ReturnsNull(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> eventFactory.createEvent(EventType.FIRE, new Room(RoomType.APARTMENT_ROOM,10,10,10,10,new ArrayList<Sensor>()), null));
    }

    @Test
    public void whenEventTypeOpenedDoor_ReturnsCorrectOpenableEvent(){
        Event event = eventFactory.createEvent(EventType.OPENED_DOOR, new Room(RoomType.APARTMENT_ROOM,10,12,14,2,new ArrayList<Sensor>()), DangerLevel.CONSIDERABLE);
        Assertions.assertTrue(event instanceof OpenableEvent);
    }

    @Test
    public void whenEventTypeOpenedWindow_ReturnsCorrectOpenableEvent(){
        Event event = eventFactory.createEvent(EventType.OPENED_WINDOW, new Room(RoomType.APARTMENT_ROOM,10,12,14,2,new ArrayList<Sensor>()), DangerLevel.CONSIDERABLE);
        Assertions.assertTrue(event instanceof OpenableEvent);
    }

    @Test
    public void whenEventTypeFire_ReturnsCorrectRoomEvent(){
        Event event = eventFactory.createEvent(EventType.FIRE, new Room(RoomType.OFFICE_ROOM,10,12,14,2, new ArrayList<Sensor>()), DangerLevel.CONSIDERABLE);
        Assertions.assertTrue(event instanceof RoomEvent);
    }

    @Test
    public void whenEventTypeFlooding_ReturnsCorrectRoomEvent(){
        Event event = eventFactory.createEvent(EventType.FLOODING, new Room(RoomType.OFFICE_ROOM,10,12,14,2, new ArrayList<Sensor>()), DangerLevel.CONSIDERABLE);
        Assertions.assertTrue(event instanceof RoomEvent);
    }

    @Test
    public void whenEventTypeMotion_ReturnsCorrectRoomEvent(){
        Event event = eventFactory.createEvent(EventType.MOTION, new Room(RoomType.APARTMENT_ROOM,10,12,14,2, new ArrayList<Sensor>()), DangerLevel.CONSIDERABLE);
        Assertions.assertTrue(event instanceof RoomEvent);
    }

    @Test
    public void whenEventTyeGasLeak_ReturnsCorrectRoomEvent(){
        Event event = eventFactory.createEvent(EventType.GAS_LEAK, new Room(RoomType.APARTMENT_ROOM,10,12,14,2, new ArrayList<Sensor>()), DangerLevel.CONSIDERABLE);
        Assertions.assertTrue(event instanceof RoomEvent);
    }
}
