package ua.lpnu.security_system_simulator.model.event;

import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.util.Date;

@Component
public class EventFactory {
    public Event createEvent(EventType eventType, EventTarget eventTarget, DangerLevel dangerLevel) throws InvalidParameterException {
        return this.createEvent(eventType, eventTarget, dangerLevel, new Date());
    }

    public Event createEvent(EventType eventType, EventTarget eventTarget, DangerLevel dangerLevel, Date happenedAt) throws InvalidParameterException {
        if(eventType == null || eventTarget == null || dangerLevel == null){
            throw new InvalidParameterException("Some parameters are null");
        }

        return switch(eventType) {
            case OPENED_WINDOW, OPENED_DOOR -> new OpenableEvent(eventType, eventTarget, dangerLevel, happenedAt);
            default -> new RoomEvent(eventType, eventTarget, dangerLevel, happenedAt);
        };
    }
}
