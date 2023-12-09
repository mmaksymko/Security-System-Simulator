package ua.lpnu.security_system_simulator.model.sensor;

import ua.lpnu.security_system_simulator.model.event.EventType;

public class OpenedWindowSensor extends Sensor {
    public OpenedWindowSensor(int coverageArea){
        super(coverageArea, EventType.OPENED_WINDOW);
    }

    @Override
    public void triggerEvent() {
        // NOT IMPLEMENTED YET
    }
}
