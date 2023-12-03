package ua.lpnu.security_system_simulator.model.sensor;

import ua.lpnu.security_system_simulator.model.event.EventType;

public class MotionSensor extends Sensor {
    public MotionSensor(int coverageArea){
        super(coverageArea);
    }

    @Override
    public void triggerEvent() {
        // NOT IMPLEMENTED YET
    }

    @Override
    public EventType getType() {
        return EventType.MOTION;
    }
}
