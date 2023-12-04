package ua.lpnu.security_system_simulator.model.event;

import ua.lpnu.security_system_simulator.model.building.Room;
import ua.lpnu.security_system_simulator.model.sensor.Sensor;

public class RoomEvent extends Event {

    RoomEvent(EventType eventType, Room room, DangerLevel dangerLevel){
        super(eventType, room, dangerLevel);
    }

    @Override
    public void start() {
        var sensors = location.getAllSensorsOfType(eventType);

        if(random.nextDouble(0,1) < (double) getCoverageArea() / location.getArea()){
            sensors.get(random.nextInt(0,sensors.size())).triggerEvent();
        }
    }

    public int getCoverageArea(){
        return Math.min(location.getAllSensorsOfType(eventType).stream().mapToInt(Sensor::getCoverageArea).sum(), location.getArea());
    }
}
