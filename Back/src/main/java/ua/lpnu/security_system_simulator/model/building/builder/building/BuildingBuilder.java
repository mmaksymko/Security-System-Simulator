package ua.lpnu.security_system_simulator.model.building.builder.building;

import ua.lpnu.security_system_simulator.model.building.BuildingLevel;

public interface BuildingBuilder {
    void seNumberOfFloors(int floors) throws Exception;
    void setNumberOfRoomsPerFloor(int rooms) throws Exception;
    void setName(String name);
    BuildingLevel build();
    void reset();
}
