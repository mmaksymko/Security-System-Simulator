package ua.lpnu.security_system_simulator.model.building.builder.building;

import ua.lpnu.security_system_simulator.model.building.BuildingComponent;
import ua.lpnu.security_system_simulator.model.building.BuildingIterator;
import ua.lpnu.security_system_simulator.model.building.BuildingLevel;
import ua.lpnu.security_system_simulator.model.building.Room;
import ua.lpnu.security_system_simulator.model.building.builder.room.OfficeRoomBuilder;
import ua.lpnu.security_system_simulator.model.building.builder.room.RoomBuilder;
import ua.lpnu.security_system_simulator.model.building.builder.room.RoomDirector;

public class OfficeBuildingBuilder implements BuildingBuilder {
    BuildingLevel result;
    public OfficeBuildingBuilder(){
        result = new BuildingLevel("default office building");
    }

    @Override
    public void seNumberOfFloors(int floors) {
        for(int i = 1; i <= floors; ++i){
            BuildingLevel newFloor = new BuildingLevel("floor " + i);
            result.addComponent(newFloor);
        }
    }

    @Override
    public void setNumberOfRoomsPerFloor(int rooms) throws Exception {
        int numberOfBlocks = rooms / 5;
        BuildingIterator iterator = new BuildingIterator(result, true);
        while (iterator.hasNext()){
            BuildingComponent currentBuildingLevel = iterator.next();
            if(currentBuildingLevel instanceof Room){
                break;
            }
            BuildingLevel currentFloor = (BuildingLevel)currentBuildingLevel;
            int floorNumber = Integer.parseInt(currentFloor.getName().split(" ")[0]);
            OfficeRoomBuilder builder = new OfficeRoomBuilder();
            RoomDirector director = new RoomDirector(floorNumber);

            for(int i = 0; i < numberOfBlocks; ++i){
                director.constructBigOfficeRoom(builder);
                currentFloor.addComponent(builder.build());
                builder.reset();

                director.constructOfficeKitchen(builder);
                currentFloor.addComponent(builder.build());
                builder.reset();

                director.constructSmallOfficeRoom(builder);
                currentFloor.addComponent(builder.build());
                builder.reset();

                director.constructSmallOfficeRoom(builder);
                currentFloor.addComponent(builder.build());
                builder.reset();

                director.constructOfficeRestroom(builder);
                currentFloor.addComponent(builder.build());
                builder.reset();
            }
        }
    }
}
