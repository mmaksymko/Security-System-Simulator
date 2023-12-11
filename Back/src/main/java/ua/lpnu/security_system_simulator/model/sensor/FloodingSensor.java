package ua.lpnu.security_system_simulator.model.sensor;

import ua.lpnu.security_system_simulator.model.event.DangerLevel;
import ua.lpnu.security_system_simulator.model.event.Event;
import ua.lpnu.security_system_simulator.model.event.EventType;

public class FloodingSensor extends Sensor {
    public FloodingSensor(int coverageArea){
        super(coverageArea, EventType.FLOODING);
    }

    @Override
    public Event triggerEvent(Event event) {
            try {
                Thread.sleep(random.nextInt(1000, 10000));
                boolean reaction = random.nextInt(0,100) < 95 && event.getDangerLevel() == DangerLevel.LOW;
                return eventFactory.createEvent(EventType.FLOODING_REACTION, event.getLocation(), event.getDangerLevel(), event.getHappenedAt(), reaction);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
    }
}
