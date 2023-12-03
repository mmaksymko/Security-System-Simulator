package ua.lpnu.security_system_simulator.model.building;

import java.util.List;

public interface BuildingComponent {

    void addComponent(BuildingComponent child);

    void removeComponent(BuildingComponent child);

    List<BuildingComponent> getComponents();

    int getNumberOfComponents();
    default Iterable<BuildingComponent> breadthFirstSearch() {
        return () -> new BuildingIterator(this, true);
    }

    default Iterable<BuildingComponent> depthFirstSearch() {
        return () -> new BuildingIterator(this, false);
    }
}
