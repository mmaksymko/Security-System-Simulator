package ua.lpnu.security_system_simulator.model.building.builder;

import ua.lpnu.security_system_simulator.model.building.RoomType;

public class RoomDirector {
    public int roomNumber;
    public void constructApartmentRoom(Builder builder){
        builder.setRoomType(RoomType.APARTMENT_ROOM);

    }

}
