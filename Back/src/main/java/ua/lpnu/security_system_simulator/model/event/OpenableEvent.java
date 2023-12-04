package ua.lpnu.security_system_simulator.model.event;

import ua.lpnu.security_system_simulator.model.building.Room;

public class OpenableEvent extends Event{
    OpenableEvent(EventType eventType, Room room, DangerLevel dangerLevel){
        super(eventType, room, dangerLevel);
    }

    @Override
    public void start() {
        var sensors = getLocation().getAllSensorsOfType(getEventType());
        if (random.nextDouble(0, 1) < (double) sensors.size() / super.getLocation().getWindows()){
            sensors.get(random.nextInt(0,sensors.size())).triggerEvent();

        }
    }
}
