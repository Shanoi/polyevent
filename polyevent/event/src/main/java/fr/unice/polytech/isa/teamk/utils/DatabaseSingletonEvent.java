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

    private List<Event> waitingList;

    private int currentID;

    public DatabaseSingletonEvent() {

    }

    @PostConstruct
    void init() {

        this.waitingList = new ArrayList<>();
        currentID = 1;
    }

    public void addToWaitingList(Event event) {
        this.waitingList.add(event);
        event.setID(String.valueOf(this.currentID++));
    }

    public void validateEvent(Event event) {
        this.waitingList.remove(event);
    }

    public List<Event> getWaitingList() {
        return waitingList;
    }

    public Optional<Event> findEventByID(String idEvent) {
        for (Event event :
                this.waitingList) {
            if (event.getID().equals(idEvent)) {
                return Optional.of(event);
            }
        }

        return Optional.empty();
    }

}
