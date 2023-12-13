package ua.lpnu.security_system_simulator.model.system;

import ua.lpnu.security_system_simulator.model.event.Event;
import ua.lpnu.security_system_simulator.model.event.EventType;

import java.util.ArrayList;
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
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public int size(){
        return events.size();
    }

    public void log(Event event) {
        if (event.getEventType() == EventType.SIMULATION_START){
            events.clear();
        }
        events.add(event);
    }
}
