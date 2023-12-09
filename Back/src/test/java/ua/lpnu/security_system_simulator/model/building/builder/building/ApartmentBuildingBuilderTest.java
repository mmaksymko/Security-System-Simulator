package ua.lpnu.security_system_simulator.model.building.builder.building;

import org.junit.jupiter.api.Test;
import ua.lpnu.security_system_simulator.model.building.BuildingLevel;

import static org.junit.jupiter.api.Assertions.*;

class ApartmentBuildingBuilderTest {
    @Test
    public void buildingBuilder(){
        OfficeBuildingBuilder builder = new OfficeBuildingBuilder();
        try {
            builder.seNumberOfFloors(5);
            builder.setNumberOfRoomsPerFloor(12);
        }catch (Exception e){
            e.getMessage();
        }
        BuildingLevel r = builder.build();
        BuildingLevel res = r;
    }


}