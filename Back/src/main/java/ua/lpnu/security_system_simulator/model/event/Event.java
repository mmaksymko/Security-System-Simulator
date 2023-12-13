package ua.lpnu.security_system_simulator.model.event;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;
import ua.lpnu.security_system_simulator.config.EventDeserializer;
import ua.lpnu.security_system_simulator.config.EventSerializer;
import ua.lpnu.security_system_simulator.model.building.Room;

import java.util.Date;
import java.util.Random;

@JsonSerialize(using = EventSerializer.class)
@JsonDeserialize(using = EventDeserializer.class)
public abstract class Event {
    @Transient
    private EventTarget location;
    private DangerLevel dangerLevel;
    private EventType eventType;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date happenedAt;
    private boolean result;
    @Transient
    protected final Random random;

    Event(){
        this.random = new Random();
    }

    Event(EventType eventType, EventTarget location, DangerLevel dangerLevel, Date happenedAt) throws InterruptedException {
        this();
        this.location = location;
        this.dangerLevel = dangerLevel;
        this.eventType = eventType;
        this.happenedAt = happenedAt;
        this.location.registerEvent(this);
        this.result = calculateResult();
    }

    Event(EventType eventType, EventTarget location, DangerLevel dangerLevel, Date happenedAt, boolean result) {
        this();
        this.location = location;
        this.dangerLevel = dangerLevel;
        this.eventType = eventType;
        this.happenedAt = happenedAt;
        this.location.registerEvent(this);
        this.result = result;
    }
    public abstract boolean calculateResult() throws InterruptedException;
    public abstract Event start() throws InterruptedException;

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

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Date getHappenedAt() {
        return happenedAt;
    }

    private void setHappenedAt(Date happenedAt) {
        this.happenedAt = happenedAt;
    }

    public boolean getResult() {
        return result;
    }

    public void setLocation(EventTarget location) {
        this.location = location;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
