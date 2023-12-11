package ua.lpnu.security_system_simulator.model.sensor;

import ua.lpnu.security_system_simulator.model.event.DangerLevel;
import ua.lpnu.security_system_simulator.model.event.Event;
import ua.lpnu.security_system_simulator.model.event.EventType;

public class GasLeakSensor extends Sensor {
    public GasLeakSensor(int coverageArea){
        super(coverageArea, EventType.GAS_LEAK);
    }
    @Override
    public Event triggerEvent(Event event) {
            try {
                Thread.sleep(random.nextInt(100, 500));
                boolean reaction = random.nextInt(0,100) < 90 && event.getDangerLevel() == DangerLevel.LOW;
                return eventFactory.createEvent(EventType.GAS_LEAK_REACTION, event.getLocation(), event.getDangerLevel(), event.getHappenedAt(), reaction);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
    }

}
