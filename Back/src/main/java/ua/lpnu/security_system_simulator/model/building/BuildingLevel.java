package ua.lpnu.security_system_simulator.model.building;

import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Document
public class BuildingLevel implements BuildingComponent {

    private List<BuildingComponent> components;
    private String name;
    public BuildingLevel() {
        this("");
    }
    public BuildingLevel(String name) {
        this(name, new LinkedList<>());
    }
    public BuildingLevel(String name, List<BuildingComponent> components) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Level with " + getNumberOfComponents() + " sublevels";
    }
}
