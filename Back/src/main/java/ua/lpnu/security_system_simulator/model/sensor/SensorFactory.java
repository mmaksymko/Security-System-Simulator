package ua.lpnu.security_system_simulator.model.sensor;

import org.springframework.stereotype.Component;
import ua.lpnu.security_system_simulator.model.event.EventType;

import java.security.InvalidParameterException;

@Component
public class SensorFactory {
    public Sensor createSensor(EventType event, int coverageArea) throws InvalidParameterException {
        return switch(event){
            case FIRE -> new FireSensor(coverageArea);
            case FLOODING -> new FloodingSensor(coverageArea);
            case GAS_LEAK -> new GasLeakSensor(coverageArea);
            case MOTION -> new MotionSensor(coverageArea);
            case OPENED_WINDOW -> new OpenedWindowSensor(coverageArea);
            case OPENED_DOOR -> new OpenedDoorSensor(coverageArea);
            case SIMULATION_START -> throw new InvalidParameterException("Such sensor cannot be created");
            default -> throw new InvalidParameterException("Some Parameters Are Null");
        };
    }
}