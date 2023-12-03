package ua.lpnu.security_system_simulator.model.building;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// @Component
public class BuildingLevel implements BuildingComponent {

    private final List<BuildingComponent> components;

    public BuildingLevel() {
        this.components = new ArrayList<>();
    }

    @Override
    public void addComponent(BuildingComponent child) {
        components.add(child);
    }

    @Override
    public void removeComponent(BuildingComponent child) {
        components.remove(child);
    }

    @Override
    public List<BuildingComponent> getComponents() {
        return Collections.unmodifiableList(components);
    }

    @Override
    public int getNumberOfComponents() {
        return components.size();
    }

    @Override
    public String toString() {
        return "Level with " + getNumberOfComponents() + " sublevels";
    }
}
