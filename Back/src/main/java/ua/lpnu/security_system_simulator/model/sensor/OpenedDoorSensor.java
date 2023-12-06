package ua.lpnu.security_system_simulator.model.sensor;

import ua.lpnu.security_system_simulator.model.event.EventType;

public class OpenedDoorSensor extends Sensor {
    public OpenedDoorSensor(int coverageArea){
        super(coverageArea, EventType.OPENED_DOOR);
    }

    @Override
    public void triggerEvent() {
        // NOT IMPLEMENTED YET
    }
}
