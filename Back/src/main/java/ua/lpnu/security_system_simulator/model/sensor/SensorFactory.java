package ua.lpnu.security_system_simulator.model.sensor;

import org.springframework.stereotype.Component;
import ua.lpnu.security_system_simulator.model.event.EventType;

@Component
public class SensorFactory {
    public Sensor createSensor(EventType event, int coverageArea) {
        return switch(event){
            case FIRE -> new FireSensor(coverageArea);
            case FLOODING -> new FloodingSensor(coverageArea);
            case GAS_LEAK -> new GasLeakSensor(coverageArea);
            case MOTION -> new MotionSensor(coverageArea);
            case OPENED_WINDOW -> new OpenedWindowSensor(coverageArea);
            case OPENED_DOOR -> new OpenedDoorSensor(coverageArea);
            case null -> null;
        };
    }
}