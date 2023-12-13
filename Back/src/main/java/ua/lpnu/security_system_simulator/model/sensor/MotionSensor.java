package ua.lpnu.security_system_simulator.model.sensor;

import ua.lpnu.security_system_simulator.model.event.DangerLevel;
import ua.lpnu.security_system_simulator.model.event.Event;
import ua.lpnu.security_system_simulator.model.event.EventType;

public class MotionSensor extends Sensor {
    public MotionSensor(int coverageArea){
        super(coverageArea, EventType.MOTION);
    }

    @Override
    public Event triggerEvent(Event event) {
            try {
                Thread.sleep(random.nextInt(0, 250));
                boolean reaction = random.nextInt(0,100) < 97 && event.getDangerLevel() == DangerLevel.LOW;
                return eventFactory.createEvent(event.getEventType(), event.getLocation(), event.getDangerLevel(), event.getHappenedAt(), reaction);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
    }
}
