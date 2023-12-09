package ua.lpnu.security_system_simulator.model.event;

import ua.lpnu.security_system_simulator.model.building.Room;

public class OpenableEvent extends Event{

    OpenableEvent(){
        super();
    }

    OpenableEvent(EventType eventType, EventTarget location, DangerLevel dangerLevel){
        super(eventType, location, dangerLevel);
    }

    @Override
    public void start() {
        Room room = (Room) getLocation();
        var sensors = room.getAllSensorsOfType(getEventType());
        if (random.nextDouble(0, 1) < (double) sensors.size() / room.getWindows()){
            sensors.get(random.nextInt(0,sensors.size())).triggerEvent();
        }
    }
}
