package ua.lpnu.security_system_simulator.model.building.builder.building;

import ua.lpnu.security_system_simulator.model.building.BuildingComponent;
import ua.lpnu.security_system_simulator.model.building.BuildingIterator;
import ua.lpnu.security_system_simulator.model.building.BuildingLevel;
import ua.lpnu.security_system_simulator.model.building.Room;
import ua.lpnu.security_system_simulator.model.building.builder.room.ApartmentRoomBuilder;
import ua.lpnu.security_system_simulator.model.building.builder.room.RoomDirector;

public class ApartmentBuildingBuilder implements BuildingBuilder {
    private BuildingLevel result;
    public ApartmentBuildingBuilder(){
        result = new BuildingLevel("default apartment building");
    }

    @Override
    public void seNumberOfFloors(int floors) throws Exception{
        if(floors < 1 || floors > 200){
            throw new IllegalArgumentException("Number of floors must be in bounds of 1 up to 200");
        }
        for(int i = 1; i <= floors; ++i){
            BuildingLevel newFloor = new BuildingLevel("floor " + i);
            result.addComponent(newFloor);
        }
    }

    @Override
    public void setNumberOfRoomsPerFloor(int rooms) throws Exception{
        if(rooms < 4 || rooms > 16){
            throw  new IllegalArgumentException("Number of rooms in one floor of apartment building must be in bounds of 4 up to 16");
        }
        if(rooms % 4 != 0){
            throw new IllegalArgumentException("Number of rooms must be multiple of 4");
        }
        int numberOfBlocks = rooms / 4;
        BuildingIterator iterator = new BuildingIterator(result, true);
        iterator.next();
        while (iterator.hasNext()){
            BuildingComponent currentBuildingLevel = iterator.next();
            if(currentBuildingLevel instanceof Room){
                break;
            }
            BuildingLevel currentFloor = (BuildingLevel)currentBuildingLevel;
            int floorNumber = Integer.parseInt(currentFloor.getName().split(" ")[1]);
            ApartmentRoomBuilder builder = new ApartmentRoomBuilder();
            RoomDirector director = new RoomDirector(floorNumber);

            for(int i = 0; i < numberOfBlocks; ++i){
                director.constructApartmentBathroom(builder);
                currentFloor.addComponent(builder.build());
                builder.reset();

                director.constructApartmentKitchen(builder);
                currentFloor.addComponent(builder.build());
                builder.reset();

                director.constructApartmentRoom(builder);
                currentFloor.addComponent(builder.build());
                builder.reset();

                director.constructApartmentRoom(builder);
                currentFloor.addComponent(builder.build());
                builder.reset();
            }
        }
    }

    @Override
    public BuildingLevel build() {
        return result;
    }

    @Override
    public void reset() {
        result = new BuildingLevel("default apartment building");
    }
}
