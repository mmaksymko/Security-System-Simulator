package ua.lpnu.security_system_simulator.model.event;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Transient;
import ua.lpnu.security_system_simulator.config.EventDeserializer;
import ua.lpnu.security_system_simulator.config.EventSerializer;
import ua.lpnu.security_system_simulator.model.building.Room;

import java.util.Random;

@JsonSerialize(using = EventSerializer.class)
@JsonDeserialize(using = EventDeserializer.class)
public abstract class Event {
    @Transient
    private EventTarget location;
    private DangerLevel dangerLevel;
    private EventType eventType;
    @Transient
    protected final Random random;

    public Event(){
        this.random = new Random();
    }

    public Event(EventType eventType, EventTarget location, DangerLevel dangerLevel) {
        this();
        this.location = location;
        this.dangerLevel = dangerLevel;
        this.eventType = eventType;
        location.registerEvent(this);
    }

    public abstract void start();

    public EventTarget getLocation() {
        return location;
    }

    public void setLocation(Room location) {
        this.location = location;
    }

    public DangerLevel getDangerLevel() {
        return dangerLevel;
    }

    public void setDangerLevel(DangerLevel dangerLevel) {
        this.dangerLevel = dangerLevel;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setLocation(EventTarget location) {
        this.location = location;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}
