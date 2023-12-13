package ua.lpnu.security_system_simulator.model.sensor.observer;

import ua.lpnu.security_system_simulator.model.event.Event;

public interface Observer {
    public void update(Event event);
}
