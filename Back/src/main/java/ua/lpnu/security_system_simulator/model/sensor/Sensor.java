package ua.lpnu.security_system_simulator.model.sensor;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.data.annotation.Transient;
import ua.lpnu.security_system_simulator.config.SensorDeserializer;
import ua.lpnu.security_system_simulator.model.event.Event;
import ua.lpnu.security_system_simulator.model.event.EventFactory;
import ua.lpnu.security_system_simulator.model.event.EventType;

import java.util.Objects;
import java.util.Random;

@JsonDeserialize(using = SensorDeserializer.class)
public abstract class Sensor {
    private EventType eventType;
    private int coverageArea;
    @Transient
    protected final EventFactory eventFactory;
    @Transient
    protected final Random random;

    public Sensor(int coverageArea, EventType eventType) throws IllegalArgumentException{
        if (coverageArea <= 0){
            throw new IllegalArgumentException("Coverage area can't be negative or 0");
        }

        this.coverageArea = coverageArea;
        this.eventType = eventType;
        this.eventFactory = new EventFactory();
        this.random = new Random();
    }

    public abstract Event triggerEvent(Event event) throws InterruptedException;

    public void setCoverageArea(int coverageArea) throws IllegalArgumentException{
        if (coverageArea <= 0){
            throw new IllegalArgumentException("Coverage area can't be negative or 0");
        }

        this.coverageArea = coverageArea;
    }

    public int getCoverageArea(){
        return coverageArea;
    }

    public EventType getType() {
        return eventType;
    }
    public void setType(EventType eventType) {
        this.eventType = eventType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Sensor sensor = (Sensor) o;
        return coverageArea == sensor.coverageArea && sensor.getType().equals(getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(coverageArea);
    }
}
