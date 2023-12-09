package ua.lpnu.security_system_simulator.model.building.builder.building;

import org.junit.jupiter.api.Test;
import ua.lpnu.security_system_simulator.model.building.BuildingLevel;

import static org.junit.jupiter.api.Assertions.*;

class ApartmentBuildingBuilderTest {
    @Test
    public void buildingBuilder(){
        ApartmentBuildingBuilder builder = new ApartmentBuildingBuilder();
        builder.seNumberOfFloors(5);
        try {
            builder.setNumberOfRoomsPerFloor(12);
        }catch (Exception e){
            e.getMessage();
        }
        BuildingLevel r = builder.build();
        BuildingLevel res = r;
    }

}