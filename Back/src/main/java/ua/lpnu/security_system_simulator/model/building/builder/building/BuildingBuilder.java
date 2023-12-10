package ua.lpnu.security_system_simulator.model.building.builder.building;

import ua.lpnu.security_system_simulator.model.building.BuildingLevel;

//TODO building builder
public interface BuildingBuilder {
    public void seNumberOfFloors(int floors) throws Exception;
    public void setNumberOfRoomsPerFloor(int rooms) throws Exception;
    public BuildingLevel build();
    void reset();
}
