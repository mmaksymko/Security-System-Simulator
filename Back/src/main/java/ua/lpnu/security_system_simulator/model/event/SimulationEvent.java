package ua.lpnu.security_system_simulator.model.event;

import java.util.Date;

public class SimulationEvent extends Event{

    SimulationEvent(){
        super();
    }

    public SimulationEvent(EventType eventType, EventTarget location, DangerLevel dangerLevel, Date happenedAt){
        super(eventType, location, dangerLevel,happenedAt);
    }

    @Override
    public void start() {}
}
