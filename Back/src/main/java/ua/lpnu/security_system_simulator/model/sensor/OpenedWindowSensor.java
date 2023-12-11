package ua.lpnu.security_system_simulator.model.sensor;

import ua.lpnu.security_system_simulator.model.event.Event;
import ua.lpnu.security_system_simulator.model.event.EventType;

public class OpenedWindowSensor extends Sensor {
    public OpenedWindowSensor(int coverageArea){
        super(coverageArea, EventType.OPENED_WINDOW);
    }

    @Override
    public Event triggerEvent(Event event) {
            try {
                Thread.sleep(random.nextInt(0, 1000));
                boolean reaction = random.nextInt(0,1000) < 97.5;
                return eventFactory.createEvent(event.getEventType(), event.getLocation(), event.getDangerLevel(), event.getHappenedAt(), reaction);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
    }
}
