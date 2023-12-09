package ua.lpnu.security_system_simulator.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ua.lpnu.security_system_simulator.model.event.Event;

import java.io.IOException;

public class EventSerializer extends StdSerializer<Event> {

    public EventSerializer() {
        this(null);
    }

    public EventSerializer(Class<Event> t) {
        super(t);
    }

    @Override
    public void serialize(Event event, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException{
        jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("eventType", event.getEventType().toString());
            jsonGenerator.writeObjectField("happenedAt", event.getHappenedAt());
            jsonGenerator.writeStringField("dangerLevel", event.getDangerLevel().toString());
        jsonGenerator.writeEndObject();
    }
}