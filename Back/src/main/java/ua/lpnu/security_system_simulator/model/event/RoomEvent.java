package ua.lpnu.security_system_simulator.model.event;

import ua.lpnu.security_system_simulator.model.building.Room;
import ua.lpnu.security_system_simulator.model.sensor.Sensor;

public class RoomEvent extends Event {

    RoomEvent() {
        super();
    }

    RoomEvent(EventType eventType, EventTarget location, DangerLevel dangerLevel) {
        super(eventType, location, dangerLevel);
    }

    @Override
    public void start() {
        if (getLocation() instanceof Room room) {
            var sensors = room.getAllSensorsOfType(getEventType());
            if (random.nextDouble(0, 1) < (double) getCoverageArea() / room.getArea()) {
                sensors.get(random.nextInt(0, sensors.size())).triggerEvent();
            }
        }
    }

    public int getCoverageArea() {
        if (getLocation() instanceof Room room)
            return Math.min(room.getAllSensorsOfType(getEventType()).stream().mapToInt(Sensor::getCoverageArea).sum(),
                    room.getArea());
        else return 0;
    }
}
