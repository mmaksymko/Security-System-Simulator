package ua.lpnu.security_system_simulator.model.sensor;

import ua.lpnu.security_system_simulator.model.event.DangerLevel;
import ua.lpnu.security_system_simulator.model.event.Event;
import ua.lpnu.security_system_simulator.model.event.EventFactory;
import ua.lpnu.security_system_simulator.model.event.EventType;

public class OpenedDoorSensor extends Sensor {
    public OpenedDoorSensor(int coverageArea){
        super(coverageArea, EventType.OPENED_DOOR);
    }

    @Override
    public Event triggerEvent(Event event) {
            try {
                Thread.sleep(random.nextInt(0, 300));
                boolean reaction = random.nextInt(0,100) < 99;
                return eventFactory.createEvent(event.getEventType(), event.getLocation(), event.getDangerLevel(), event.getHappenedAt(), reaction);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
    }
}
