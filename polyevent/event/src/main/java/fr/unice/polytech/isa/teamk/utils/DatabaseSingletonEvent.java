package fr.unice.polytech.isa.teamk.utils;

import fr.unice.polytech.isa.teamk.entities.Event;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Startup
@Singleton
public class DatabaseSingletonEvent {

    private List<Event> events;

    private int currentID;

    public DatabaseSingletonEvent() {

    }

    @PostConstruct
    void init() {
        this.events = new ArrayList<>();
        currentID = 1;
    }

    public void addToWaitingList(Event event) {
        this.events.add(event);
        event.setID(String.valueOf(this.currentID++));
    }

    public void validateEvent(Event event) {
        this.events.remove(event);
    }

    public List<Event> getEvents() {
        return events;
    }

    public Optional<Event> findEventByID(String idEvent) {
        for (Event event :
                this.events) {
            if (event.getID().equals(idEvent)) {
                return Optional.of(event);
            }
        }

        return Optional.empty();
    }

    public void registerEvent(Event event) {
        this.events.add(event);
        event.setID(String.valueOf(this.currentID++));
    }

}
