package ua.lpnu.security_system_simulator.model.event;

import org.springframework.stereotype.Component;
import ua.lpnu.security_system_simulator.model.building.Room;

import java.security.InvalidParameterException;

@Component
public class EventFactory {
    public Event createEvent(EventType eventType, EventTarget eventTarget, DangerLevel dangerLevel) throws InvalidParameterException {
        if(eventType == null || eventTarget == null || dangerLevel == null){
            throw new InvalidParameterException("Some parameters are null");
        }

        return switch(eventType) {
            case OPENED_WINDOW, OPENED_DOOR -> new OpenableEvent(eventType, eventTarget, dangerLevel);
            default -> new RoomEvent(eventType, eventTarget, dangerLevel);
        };
    }
}
