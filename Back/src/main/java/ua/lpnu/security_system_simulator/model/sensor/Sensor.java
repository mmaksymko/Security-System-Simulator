package ua.lpnu.security_system_simulator.model.sensor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.lpnu.security_system_simulator.model.event.EventType;
import ua.lpnu.security_system_simulator.model.sensor.observer.Observer;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Sensor {

    private int coverageArea;
    private ArrayList<Observer> observers;

    public abstract void triggerEvent();
    public abstract EventType getType();

    public Sensor(int coverageArea) throws IllegalArgumentException{
        if (coverageArea <= 0){
            throw new IllegalArgumentException("Coverage area can't be negative or 0");
        }

        this.coverageArea = coverageArea;
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
