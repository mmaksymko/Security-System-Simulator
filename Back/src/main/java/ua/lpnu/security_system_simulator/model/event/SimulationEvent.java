package ua.lpnu.security_system_simulator.model.event;

import java.util.Date;

public class SimulationEvent extends Event{

    SimulationEvent(){
        super();
    }

    SimulationEvent(EventType eventType, EventTarget location, DangerLevel dangerLevel, Date happenedAt) throws InterruptedException {
        super(eventType, location, dangerLevel,happenedAt);
    }

    SimulationEvent(EventType eventType, EventTarget location, DangerLevel dangerLevel, Date happenedAt, boolean result) {
        super(eventType, location, dangerLevel, happenedAt, result);
    }

    @Override
    public boolean calculateResult() {
        return true;
    }

    @Override
    public Event start() throws InterruptedException, UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
