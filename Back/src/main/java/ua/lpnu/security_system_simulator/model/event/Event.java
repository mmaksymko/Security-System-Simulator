package ua.lpnu.security_system_simulator.model.event;

import ua.lpnu.security_system_simulator.model.building.Room;

import java.util.Random;

public abstract class Event {
    private Room location;
    private DangerLevel dangerLevel;
    private final EventType eventType;
    protected final Random random;

    public Event(EventType eventType, Room location, DangerLevel dangerLevel) {
        this.eventType = eventType;
        this.location = location;
        this.dangerLevel = dangerLevel;
        this.random = new Random();
    }

    public abstract void start();

    public Room getLocation() {
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
}
