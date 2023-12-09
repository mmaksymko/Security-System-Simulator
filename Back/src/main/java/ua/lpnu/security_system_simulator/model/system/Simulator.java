package ua.lpnu.security_system_simulator.model.system;

import ua.lpnu.security_system_simulator.model.building.BuildingLevel;

public class Simulator {
    private BuildingLevel building;
    public Simulator() {
        this(null);
    }

    public Simulator(BuildingLevel building){
        this.building = building;
    }

    public BuildingLevel getBuilding() {
        return building;
    }

    public void setBuilding(BuildingLevel building) {
        this.building = building;
    }

    public void startSimulation(){

    }

    public void stopSimulation(){

    }

    public void pauseSimulation(){

    }
}