package ua.lpnu.security_system_simulator.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ua.lpnu.security_system_simulator.model.building.Room;
import ua.lpnu.security_system_simulator.model.event.Event;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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
        if (event.getLocation() != null)
            jsonGenerator.writeNumberField("location", ((Room) event.getLocation()).getRoomNumber());
        jsonGenerator.writeStringField("dangerLevel", event.getDangerLevel().toString());
        jsonGenerator.writeBooleanField("result", event.getResult());
        jsonGenerator.writeStringField("happenedAt", ZonedDateTime.ofInstant(event.getHappenedAt().toInstant(), ZoneId.of("UTC")).format(DateTimeFormatter.ISO_INSTANT));
        jsonGenerator.writeEndObject();
    }
}