package ua.lpnu.Security.System.Simulator.model.building;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.lpnu.security_system_simulator.model.building.BuildingComponent;
import ua.lpnu.security_system_simulator.model.building.BuildingLevel;
import ua.lpnu.security_system_simulator.model.building.Room;
import ua.lpnu.security_system_simulator.model.building.RoomType;
import ua.lpnu.security_system_simulator.model.sensor.Sensor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BuildingLevelTests {
    private final BuildingLevel level;

    public BuildingLevelTests() {
        level = new BuildingLevel();
    }

    @Test
    public void emptyBuildingLevelIsCreatedCorrectly() {
        Assertions.assertEquals(level.getNumberOfComponents(), 0);
    }

    @Test
    public void emptyBuildingLevelToStringAddsComponentsCorrectly() {
        var list = getListOfComponents();
        list.forEach(level::addComponent);


        Assertions.assertEquals(level.getNumberOfComponents(), 4);
        Assertions.assertEquals(level.getComponents(), list);
    }

    @Test
    public void buildingLevelToStringRemovesComponentsCorrectly() {
        var list = getListOfComponents();
        list.forEach(level::addComponent);

        level.removeComponent(list.get(2));
        list.remove(2);

        Assertions.assertEquals(level.getNumberOfComponents(), 3);
        Assertions.assertEquals(level.getComponents(), list);
    }

    @Test
    public void buildingLevelToStringRemovesAllComponentsCorrectly() {
        var list = getListOfComponents();
        list.forEach(level::addComponent);

        list.forEach(level::removeComponent);

        Assertions.assertEquals(level.getNumberOfComponents(), 0);
        Assertions.assertEquals(level.getComponents(), new ArrayList<>());
    }


    private List<BuildingComponent> getListOfComponents(){
        return new LinkedList<>(List.of(
                new BuildingLevel(),
                new BuildingLevel(),
                new Room(RoomType.APARTMENT_ROOM,5, 2, 5, 1,new ArrayList<Sensor>()),
                new Room(RoomType.KITCHEN,1, 2, 5, 6, new ArrayList<Sensor>())));
    }
}
