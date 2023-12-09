package ua.lpnu.security_system_simulator.model.building.builder.building;

import ua.lpnu.security_system_simulator.model.building.BuildingComponent;

//TODO building builder
public interface Builder {
    public void seNumberOfFloors(int floors);
    public void setNumberOfRoomsPerFloor(int rooms) throws Exception;
    public BuildingComponent build();
    void reset();
}
