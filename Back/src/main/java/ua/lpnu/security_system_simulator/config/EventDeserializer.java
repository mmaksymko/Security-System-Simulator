package ua.lpnu.security_system_simulator.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import ua.lpnu.security_system_simulator.model.event.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class EventDeserializer extends JsonDeserializer<Event> {
    @Override
    public Event deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        var ctxt = jsonParser.getParsingContext();
        while(!((ctxt = ctxt.getParent()).getCurrentValue() instanceof EventTarget));
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        var eventType = Arrays.stream(EventType.values()).filter(type -> type.toString().equals(node.get("eventType").asText())).findFirst().orElseThrow( () -> new IOException("Bad enum value"));
        var target = (EventTarget) ctxt.getCurrentValue();
        var dangerLevel = Arrays.stream(DangerLevel.values()).filter(type -> type.toString().equals(node.get("dangerLevel").asText())).findFirst().orElseThrow( () -> new IOException("Bad enum value"));
        var result = node.get("result").asBoolean();
        String happenedAtStr = node.get("happenedAt").asText();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSUTC");
        try {
            Date happenedAt = formatter.parse(happenedAtStr);
            return new EventFactory().createEvent(eventType, target, dangerLevel, happenedAt, result);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
