package ua.lpnu.security_system_simulator.model.system;

import ua.lpnu.security_system_simulator.model.sensor.observer.LogNotifier;

public class Simulator {
    private SecuritySystem securitySystem;
    private LogNotifier logNotifier;
    private SimulatorCareTaker simulatorCareTaker;
    private Simulator state;

    public void startSimulation() {

    };
    public void stopSimulation() {};
    public void pauseSimulation() {};
    public SimulatorMomento save() {
        return null;
    };
    public void restore(SimulatorMomento momento) {};

}
