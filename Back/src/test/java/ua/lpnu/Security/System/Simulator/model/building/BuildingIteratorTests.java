package ua.lpnu.Security.System.Simulator.model.building;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.lpnu.security_system_simulator.model.building.BuildingComponent;
import ua.lpnu.security_system_simulator.model.building.BuildingLevel;
import ua.lpnu.security_system_simulator.model.building.Room;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BuildingIteratorTests {

    @Test
    public void depthFirstNextOnChildlessLevel_ReturnsItself(){
        var level = new BuildingLevel("Floor");
        var iterator = level.depthFirstSearch().iterator();
        Assertions.assertEquals(iterator.next(), level);
    }
    @Test
    public void depthFirstHasNextOnChildlessLevelAfterItself_ReturnsFalse(){
        var level = new BuildingLevel("Floor");
        var iterator = level.depthFirstSearch().iterator();
        iterator.next();
        Assertions.assertFalse(iterator.hasNext());
    }

    @Test
    public void breadthFirstNextOnChildlessLevel_ReturnsItself(){
        var level = new BuildingLevel("Floor");
        var iterator = level.breadthFirstSearch().iterator();
        Assertions.assertEquals(iterator.next(), level);
    }
    @Test
    public void breadthFirstHasNextOnChildlessLevelAfterItself_ReturnsFalse(){
       var level = new BuildingLevel("Floor");
       var iterator = level.breadthFirstSearch().iterator();
       iterator.next();
       Assertions.assertFalse(iterator.hasNext());
    }

    @Test
    public void depthFirstIteratingOnMultipleLevels_IteratesInCorrectOrder(){
        var testedLevel = new BuildingLevel("Building");

        var level1 = new BuildingLevel("Floor 1");
        var level2 = new BuildingLevel("Floor 2");
        var level2_sublevel1 = new Room(2, 2, 5, 1);
        var level2_sublevel2 = new Room(4, 2, 5, 1);
        level2.addComponent(level2_sublevel1);
        level2.addComponent(level2_sublevel2);
        var level3 = new BuildingLevel("Floor3");
        var level4 = new Room(5, 2, 5, 1);
        var level5 = new Room(1, 2, 5, 6);
        var level6  = new BuildingLevel("Floor4");
        var level6_sublevel1 = new Room(7, 700, 5, 1);
        level6.addComponent(level6_sublevel1);

        List<BuildingComponent> list = new LinkedList<>(List.of(
                level1, level2, level3, level4, level5, level6)
        );

        list.forEach(testedLevel::addComponent);

        List<BuildingComponent> iterationResult = new ArrayList<>();
        for(var sublevel : testedLevel.depthFirstSearch()){
            iterationResult.add(sublevel);
        }

        Assertions.assertEquals(iterationResult, List.of(testedLevel, level1, level2, level2_sublevel1, level2_sublevel2, level3, level4, level5, level6, level6_sublevel1));
    }

    @Test
    public void breadthFirstIteratingOnMultipleLevels_IteratesInCorrectOrder(){
        var testedLevel = new BuildingLevel("Building");

        var level1 = new BuildingLevel("Floor 1");
        var level2 = new BuildingLevel("Floor 2");
        var level2_sublevel1 = new Room(2, 2, 5, 1);
        var level2_sublevel2 = new Room(4, 2, 5, 1);
        level2.addComponent(level2_sublevel1);
        level2.addComponent(level2_sublevel2);
        var level3 = new BuildingLevel("Floor 3");
        var level4 = new Room(5, 2, 5, 1);
        var level5 = new Room(1, 2, 5, 6);
        var level6  = new BuildingLevel("Floor 4");
        var level6_sublevel1 = new Room(7, 700, 5, 1);
        level6.addComponent(level6_sublevel1);

        List<BuildingComponent> list = new LinkedList<>(List.of(
                level1, level2, level3, level4, level5, level6)
        );

        list.forEach(testedLevel::addComponent);

        List<BuildingComponent> iterationResult = new ArrayList<>();
        for(var sublevel : testedLevel.breadthFirstSearch()){
            iterationResult.add(sublevel);
        }

        Assertions.assertEquals(iterationResult, List.of(testedLevel, level1, level2, level3, level4, level5, level6,level2_sublevel1, level2_sublevel2, level6_sublevel1));
    }
}
