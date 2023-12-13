package ua.lpnu.security_system_simulator.model.building.builder.building;

import ua.lpnu.security_system_simulator.model.building.BuildingComponent;
import ua.lpnu.security_system_simulator.model.building.BuildingIterator;
import ua.lpnu.security_system_simulator.model.building.BuildingLevel;
import ua.lpnu.security_system_simulator.model.building.Room;
import ua.lpnu.security_system_simulator.model.building.builder.room.OfficeRoomBuilder;
import ua.lpnu.security_system_simulator.model.building.builder.room.RoomDirector;

public class OfficeBuildingBuilder implements BuildingBuilder {
    private BuildingLevel result;
    public OfficeBuildingBuilder(){
        result = new BuildingLevel("default office building");
    }

    @Override
    public void seNumberOfFloors(int floors) {
        if(floors < 1 || floors > 200){
            throw new IllegalArgumentException("Number of floors must be in bounds of 1 up to 200");
        }
        for(int i = 1; i <= floors; ++i){
            BuildingLevel newFloor = new BuildingLevel("floor " + i);
            result.addComponent(newFloor);
        }
    }

    @Override
    public void setNumberOfRoomsPerFloor(int rooms) throws Exception {
        if(rooms < 5 || rooms > 20){
            throw  new IllegalArgumentException("Number of rooms in one floor of apartment building must be in bounds of 5 up to 20");
        }
        if(rooms % 5 != 0){
            throw new IllegalArgumentException("Number of rooms must be multiple of 5");
        }
        int numberOfBlocks = rooms / 5;
        BuildingIterator iterator = new BuildingIterator(result, true);
        iterator.next();
        while (iterator.hasNext()){
            BuildingComponent currentBuildingLevel = iterator.next();
            if(currentBuildingLevel instanceof Room){
                break;
            }
            BuildingLevel currentFloor = (BuildingLevel)currentBuildingLevel;
            int floorNumber = Integer.parseInt(currentFloor.getName().split(" ")[1]);
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

    @Override
    public void setName(String name) {
        result.setName(name);
    }

    @Override
    public BuildingLevel build() {
        return result;
    }

    @Override
    public void reset() {
        result = new BuildingLevel("default office building");
    }
}
