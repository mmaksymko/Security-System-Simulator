package ua.lpnu.security_system_simulator.model.event;

public interface EventTarget {
    void registerEvent(Event event);
    int getRoomNumber();
}
