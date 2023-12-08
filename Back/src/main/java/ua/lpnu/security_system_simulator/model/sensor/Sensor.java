package ua.lpnu.security_system_simulator.model.sensor;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ua.lpnu.security_system_simulator.config.SensorDeserializer;
import ua.lpnu.security_system_simulator.model.event.EventType;

import java.util.Objects;

@JsonDeserialize(using = SensorDeserializer.class)
public abstract class Sensor {

    private EventType eventType;
    private int coverageArea;

    public abstract void triggerEvent();
    public EventType getType() {
        return eventType;
    }
    public void setType(EventType eventType) {
        this.eventType = eventType;
    }

    public Sensor(int coverageArea, EventType eventType) throws IllegalArgumentException{
        if (coverageArea <= 0){
            throw new IllegalArgumentException("Coverage area can't be negative or 0");
        }

        this.coverageArea = coverageArea;
        this.eventType = eventType;
    }

    public void setCoverageArea(int coverageArea) throws IllegalArgumentException{
        if (coverageArea <= 0){
            throw new IllegalArgumentException("Coverage area can't be negative or 0");
        }

        this.coverageArea = coverageArea;
    }

    public int getCoverageArea(){
        return coverageArea;
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
