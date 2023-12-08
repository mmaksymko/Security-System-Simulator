package ua.lpnu.security_system_simulator.model.sensor;

import ua.lpnu.security_system_simulator.model.event.EventType;

public class GasLeakSensor extends Sensor {
    public GasLeakSensor(int coverageArea){
        super(coverageArea, EventType.GAS_LEAK);
    }
    @Override
    public void triggerEvent() {
        // NOT IMPLEMENTED YET
    }
}
