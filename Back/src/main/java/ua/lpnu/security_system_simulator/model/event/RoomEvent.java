package ua.lpnu.security_system_simulator.model.event;

import ua.lpnu.security_system_simulator.model.building.Room;
import ua.lpnu.security_system_simulator.model.sensor.Sensor;

import java.util.Date;

public class RoomEvent extends Event {

    RoomEvent() {
        super();
    }

    RoomEvent(EventType eventType, EventTarget location, DangerLevel dangerLevel, Date happenedAt) throws InterruptedException {
        super(eventType, location, dangerLevel, happenedAt);
    }

    public RoomEvent(EventType eventType, EventTarget location, DangerLevel dangerLevel, Date happenedAt, boolean result) {
        super(eventType, location, dangerLevel, happenedAt, result);
    }

    @Override
    public boolean calculateResult() throws InterruptedException {
        boolean result = getLocation() instanceof Room;
        if (result) {
            Room room = (Room) getLocation();
            result = random.nextDouble(0, 1) < (double) getCoverageArea() / room.getArea();
        }
        return result;
    }

    @Override
    public Event start() throws InterruptedException {
        if(getResult()){
            var sensors = ((Room) getLocation()).getAllSensorsOfType(getEventType());
            return sensors.get(random.nextInt(0, sensors.size())).triggerEvent(this);
        }
        return null;
    }

    public int getCoverageArea() {
        if (getLocation() instanceof Room room)
            return Math.min(room.getAllSensorsOfType(getEventType()).stream().mapToInt(Sensor::getCoverageArea).sum(),
                    room.getArea());
        else return 0;
    }
}
