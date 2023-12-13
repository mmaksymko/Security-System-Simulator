package ua.lpnu.security_system_simulator.model.sensor;

import ua.lpnu.security_system_simulator.model.event.DangerLevel;
import ua.lpnu.security_system_simulator.model.event.Event;
import ua.lpnu.security_system_simulator.model.event.EventType;


public class FireSensor extends Sensor {
    public FireSensor(int coverageArea){
        super(coverageArea, EventType.FIRE);
    }

    @Override
    public Event triggerEvent(Event event) throws RuntimeException {
            try {
                Thread.sleep(random.nextInt(750, 2500));
                boolean reaction = random.nextInt(0,100) < 95 && event.getDangerLevel() == DangerLevel.LOW;
                return eventFactory.createEvent(EventType.FIRE_REACTION, event.getLocation(), event.getDangerLevel(), event.getHappenedAt(), reaction);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
    }
}
