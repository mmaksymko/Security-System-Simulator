package ua.lpnu.security_system_simulator.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;

import org.springframework.stereotype.Component;

import ua.lpnu.security_system_simulator.model.event.EventType;
import ua.lpnu.security_system_simulator.model.sensor.Sensor;
import ua.lpnu.security_system_simulator.model.sensor.SensorFactory;

import java.io.IOException;
import java.util.Arrays;

@Component
public class SensorDeserializer extends JsonDeserializer<Sensor> {

    @Override
    public Sensor deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        return new SensorFactory().createSensor(Arrays.stream(EventType.values()).filter(type -> type.toString().equals(node.get("type").asText())).findFirst().orElseThrow( () -> new IOException("Bad enum value")), node.get("coverageArea").asInt());
    }
}