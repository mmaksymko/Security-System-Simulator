package ua.lpnu.security_system_simulator.model.system;

import ua.lpnu.security_system_simulator.model.event.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventLog {
    private List<Event> events;

    public EventLog(){
        this(new ArrayList<>());
    }

    public EventLog(List<Event> events) {
        this.events = events;
    }

    public List<Event> getEvents() {
        return Collections.unmodifiableList(events);
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void log(Event event) {
        this.events.add(event);
    }
}
