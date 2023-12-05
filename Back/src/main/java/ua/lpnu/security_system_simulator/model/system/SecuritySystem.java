package ua.lpnu.security_system_simulator.model.system;

import ua.lpnu.security_system_simulator.model.building.BuildingComponent;
import ua.lpnu.security_system_simulator.model.event.Event;
import ua.lpnu.security_system_simulator.model.sensor.observer.Observer;

public class SecuritySystem implements Observer {
    private BuildingComponent building;
    @Override
    public void update(Event event) {
        //todo тут буде сповіщення яке система надсилатиме для відображення на юайці
    }
}
