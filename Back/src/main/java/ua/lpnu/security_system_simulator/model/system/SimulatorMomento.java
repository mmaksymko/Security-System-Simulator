package ua.lpnu.security_system_simulator.model.system;

public class SimulatorMomento {
    private Simulator state;

    private boolean isRunning;
    private boolean isPaused;

    public SimulatorMomento (Simulator state) {

    }

    public Simulator getState() {
        return state;
    }
}
