package ua.lpnu.security_system_simulator.model.building;

import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Document
public class BuildingLevel implements BuildingComponent {

    private List<BuildingComponent> components;

    public BuildingLevel() {
        this(new LinkedList<>());
    }
    public BuildingLevel(List<BuildingComponent> components) {
        this.components = components;
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
