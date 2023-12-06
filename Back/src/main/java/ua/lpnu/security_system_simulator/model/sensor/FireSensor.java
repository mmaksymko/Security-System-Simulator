package ua.lpnu.security_system_simulator.model.sensor;

import ua.lpnu.security_system_simulator.model.event.EventType;

public class FireSensor extends Sensor {
    public FireSensor(int coverageArea){
        super(coverageArea, EventType.FIRE);
    }

    @Override
    public void triggerEvent() {
        // NOT IMPLEMENTED YET
    }
}
