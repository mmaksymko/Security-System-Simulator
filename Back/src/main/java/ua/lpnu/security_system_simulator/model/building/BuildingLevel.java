package ua.lpnu.security_system_simulator.model.building;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.bson.types.ObjectId;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import ua.lpnu.security_system_simulator.config.BuildingLevelDeserializer;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@JsonDeserialize(using = BuildingLevelDeserializer.class)
@Document(collection="buildings")
public class BuildingLevel implements BuildingComponent {
    @Id
    private String id;
    private List<BuildingComponent> components;
    private String name;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createdAt;
    public BuildingLevel() {
        this("");
    }
    public BuildingLevel(String name) {
        this(name, new LinkedList<>());
    }
    public BuildingLevel(String name, List<BuildingComponent> components) {
        this.name = name;
        this.components = components;
        this.id = new ObjectId().toString();
        this.createdAt = new Date();
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

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    private void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    private void setComponents(List<BuildingComponent> components) {
        this.components = components;
    }

    @Override
    public String toString() {
        return name + " with " + getNumberOfComponents() + " sublevels";
    }
}
