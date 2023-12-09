package ua.lpnu.security_system_simulator.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ua.lpnu.security_system_simulator.model.building.*;
import ua.lpnu.security_system_simulator.model.building.builder.room.BuildingComponent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuildingLevelDeserializer extends JsonDeserializer<BuildingLevel> {

    @Override
    public BuildingLevel deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        ObjectCodec codec = jsonParser.getCodec();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = codec.readTree(jsonParser);

        List<BuildingComponent> components = new ArrayList<>();
        for(var element : node.get("components")){
            if (element.has("windows")){
                components.add(mapper.treeToValue(element, Room.class));
            }
            else {
                components.add(mapper.treeToValue(element, BuildingLevel.class));
            }
        }

        var building = new BuildingLevel(node.get("name").asText());
        components.forEach(building::addComponent);
        return building;
    }
}