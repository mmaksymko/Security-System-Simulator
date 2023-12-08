package ua.lpnu.security_system_simulator.model.building;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import ua.lpnu.security_system_simulator.config.BuildingLevelDeserializer;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@JsonDeserialize(using = BuildingLevelDeserializer.class)
@Document(collection="buildings")
public class BuildingLevel implements BuildingComponent {
    @Id
    private String id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name + " with " + getNumberOfComponents() + " sublevels";
    }

}
