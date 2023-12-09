package ua.lpnu.security_system_simulator.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import ua.lpnu.security_system_simulator.model.event.*;

import java.io.IOException;
import java.util.Arrays;

public class EventDeserializer extends JsonDeserializer<Event> {
    @Override
    public Event deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        var ctxt = jsonParser.getParsingContext();
        while(!((ctxt = ctxt.getParent()).getCurrentValue() instanceof EventTarget));
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        var eventType = Arrays.stream(EventType.values()).filter(type -> type.toString().equals(node.get("eventType").asText())).findFirst().orElseThrow( () -> new IOException("Bad enum value"));
        var dangerLevel = Arrays.stream(DangerLevel.values()).filter(type -> type.toString().equals(node.get("dangerLevel").asText())).findFirst().orElseThrow( () -> new IOException("Bad enum value"));
        return new EventFactory().createEvent(eventType,(EventTarget) ctxt.getCurrentValue(), dangerLevel);
    }
}
