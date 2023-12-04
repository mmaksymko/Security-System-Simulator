package ua.lpnu.Security.System.Simulator.model.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.lpnu.security_system_simulator.model.building.Room;
import ua.lpnu.security_system_simulator.model.event.DangerLevel;
import ua.lpnu.security_system_simulator.model.event.EventFactory;
import ua.lpnu.security_system_simulator.model.event.EventType;
import ua.lpnu.security_system_simulator.model.event.RoomEvent;
import ua.lpnu.security_system_simulator.model.sensor.SensorFactory;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {ua.lpnu.security_system_simulator.model.event.EventFactory.class, ua.lpnu.security_system_simulator.model.sensor.SensorFactory.class})
public class RoomEventTests {
    @Autowired
    private EventFactory eventFactory;
    @Autowired
    private SensorFactory sensorFactory;

    @Test
    public void getCoverageArea_Returns0_WhenNoSensorsInRoom() {
        var event = (RoomEvent) eventFactory.createEvent(EventType.FLOODING, new Room(10,50,10,10), DangerLevel.HIGH);
        Assertions.assertEquals(event.getCoverageArea(), 0);
    }

    @Test
    public void getCoverageArea_Returns0_WhenNoSensorsOfEventTypeInRoom() {
        var event = (RoomEvent) eventFactory.createEvent(EventType.FLOODING, new Room(10,50,10,10), DangerLevel.HIGH);
        event.getLocation().addSensor(sensorFactory.createSensor(EventType.FIRE, 10));
        event.getLocation().addSensor(sensorFactory.createSensor(EventType.OPENED_WINDOW, 16));
        event.getLocation().addSensor(sensorFactory.createSensor(EventType.OPENED_DOOR, 20));
        Assertions.assertEquals(event.getCoverageArea(), 0);
    }


    @Test
    public void getCoverageAreaForNotFullyCoveredRoom_ReturnsCorrectlyCalculatedInt(){
        Room room = new Room(4,80, 6, 10);
        room.addSensor(sensorFactory.createSensor(EventType.FLOODING, 11));
        room.addSensor(sensorFactory.createSensor(EventType.FLOODING, 12));
        room.addSensor(sensorFactory.createSensor(EventType.FLOODING, 7));
        room.addSensor(sensorFactory.createSensor(EventType.FLOODING, 17));
        room.addSensor(sensorFactory.createSensor(EventType.FIRE, 17));
        var event = (RoomEvent) eventFactory.createEvent(EventType.FLOODING, room, DangerLevel.HIGH);
        Assertions.assertEquals(47, event.getCoverageArea());
    }

    @Test
    public void getRoomAreaForFullyCoveredRoom_ReturnsCorrectlyCalculatedInt(){
        Room room = new Room(4,80, 6, 10);
        room.addSensor(sensorFactory.createSensor(EventType.FLOODING, 11));
        room.addSensor(sensorFactory.createSensor(EventType.FLOODING, 12));
        room.addSensor(sensorFactory.createSensor(EventType.FLOODING, 7));
        room.addSensor(sensorFactory.createSensor(EventType.FLOODING, 170));
        room.addSensor(sensorFactory.createSensor(EventType.FIRE, 17));
        var event = (RoomEvent) eventFactory.createEvent(EventType.FLOODING, room, DangerLevel.HIGH);
        Assertions.assertEquals(80, event.getCoverageArea());
    }
}
