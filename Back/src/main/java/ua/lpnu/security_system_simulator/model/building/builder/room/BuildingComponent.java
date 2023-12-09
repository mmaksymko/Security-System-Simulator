package ua.lpnu.security_system_simulator.model.building.builder.room;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ua.lpnu.security_system_simulator.model.building.BuildingIterator;

import java.util.List;

public interface BuildingComponent {

    void addComponent(BuildingComponent child);

    void removeComponent(BuildingComponent child);

    List<BuildingComponent> getComponents();

    @JsonIgnore
    int getNumberOfComponents();

    String getId();

    default Iterable<BuildingComponent> breadthFirstSearch() {
        return () -> new BuildingIterator(this, true);
    }

    default Iterable<BuildingComponent> depthFirstSearch() {
        return () -> new BuildingIterator(this, false);
    }
}
