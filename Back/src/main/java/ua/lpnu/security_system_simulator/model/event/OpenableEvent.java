package ua.lpnu.security_system_simulator.model.event;

import ua.lpnu.security_system_simulator.model.building.Room;

import java.util.Date;

public class OpenableEvent extends Event{

    OpenableEvent(){
        super();
    }

    OpenableEvent(EventType eventType, EventTarget location, DangerLevel dangerLevel, Date happenedAt) throws InterruptedException {
        super(eventType, location, dangerLevel,happenedAt);
    }

    public OpenableEvent(EventType eventType, EventTarget location, DangerLevel dangerLevel, Date happenedAt, boolean result) {
        super(eventType, location, dangerLevel, happenedAt, result);
    }

    @Override
    public boolean calculateResult() throws InterruptedException {
        Room room = (Room) getLocation();
        var sensors = room.getAllSensorsOfType(getEventType());
        boolean result = random.nextDouble(0, 1) < (double) sensors.size() / room.getWindows();
        if (result) {
            sensors.get(random.nextInt(0, sensors.size())).triggerEvent(this);
        }
        return result;
    }

    @Override
    public Event start() throws InterruptedException {
        Room room = (Room) getLocation();
        var sensors = room.getAllSensorsOfType(getEventType());
        if (getResult()) {
            return sensors.get(random.nextInt(0, sensors.size())).triggerEvent(this);
        }
        return null;
    }
}
