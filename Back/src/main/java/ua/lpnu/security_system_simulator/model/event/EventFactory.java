package ua.lpnu.security_system_simulator.model.event;

import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.util.Date;

@Component
public class EventFactory {
    public Event createEvent(EventType eventType, EventTarget eventTarget, DangerLevel dangerLevel) throws
            InvalidParameterException, InterruptedException {
        if (eventType == null || eventTarget == null || dangerLevel == null) {
            throw new InvalidParameterException("Some parameters are null");
        }

        return switch (eventType) {
            case OPENED_WINDOW, OPENED_DOOR -> new OpenableEvent(eventType, eventTarget, dangerLevel, new Date());
            case SIMULATION_START -> new SimulationEvent(eventType, eventTarget, dangerLevel, new Date());
            default -> new RoomEvent(eventType, eventTarget, dangerLevel, new Date());
        };
    }
    public Event createEvent(EventType eventType, EventTarget eventTarget, DangerLevel dangerLevel, Date happenedAt, boolean reaction) throws InvalidParameterException {
        if(eventType == null || eventTarget == null || dangerLevel == null){
            throw new InvalidParameterException("Some parameters are null");
        }

        return switch(eventType) {
            case OPENED_WINDOW, OPENED_DOOR -> new OpenableEvent(eventType, eventTarget, dangerLevel, happenedAt, reaction);
            case FIRE, FLOODING, MOTION, GAS_LEAK -> new RoomEvent(eventType, eventTarget, dangerLevel, happenedAt, reaction);
            case SIMULATION_START -> new SimulationEvent(eventType, eventTarget, dangerLevel, happenedAt, reaction);
            default -> new ReactionEvent(eventType, eventTarget, dangerLevel, happenedAt, reaction);
        };
    }
}
