package ua.lpnu.security_system_simulator.model.sensor;

import ua.lpnu.security_system_simulator.model.event.EventType;

public class MotionSensor extends Sensor {
    public MotionSensor(int coverageArea){
        super(coverageArea, EventType.MOTION);
    }

    @Override
    public void triggerEvent() {
        // NOT IMPLEMENTED YET
    }
}
