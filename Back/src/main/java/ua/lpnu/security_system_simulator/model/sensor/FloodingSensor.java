package ua.lpnu.security_system_simulator.model.sensor;

import ua.lpnu.security_system_simulator.model.event.EventType;

public class FloodingSensor extends Sensor {
    public FloodingSensor(int coverageArea){
        super(coverageArea, EventType.FLOODING);
    }

    @Override
    public void triggerEvent() {
        // NOT IMPLEMENTED YET
    }
}
