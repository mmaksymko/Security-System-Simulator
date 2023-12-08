package ua.lpnu.security_system_simulator.model.sensor.observer;

import ua.lpnu.security_system_simulator.model.event.Event;

import java.util.List;

public class LogNotifier implements Observer{
    private List<Event> events;
    public LogNotifier(List<Event> events){
        this.events = events;
    }
    public LogNotifier(LogNotifier logNotifier){
        this.events = logNotifier.events;
    }

    @Override
    public void update(Event event) {
        //TODO логування
    }
    public void clearLog(){
        //TODO хз я вважаю не обов'язково чищення логу
    }
    public List<Event> getAllEvents(){
        return events;
    }
}
