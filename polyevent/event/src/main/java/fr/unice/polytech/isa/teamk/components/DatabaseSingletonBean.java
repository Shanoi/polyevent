package fr.unice.polytech.isa.teamk.components;

import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.entities.users.Organizer;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.*;

@Startup
@Singleton
public class DatabaseSingletonBean {

    private Set<Organizer> organizerList;

    private List<Event> waitingList;

    private int currentID;

    public DatabaseSingletonBean() {

    }

    @PostConstruct
    void init() {
        this.organizerList = new HashSet<>();
        this.waitingList = new ArrayList<>();
        currentID = 1;
    }

    public Optional<Organizer> findOrganizerByID(String id) {
        for (Organizer organizer : organizerList) {
            if (organizer.getId().equals(id)) {
                return Optional.of(organizer);
            }
        }

        return Optional.empty();
    }

    public boolean createNewOrganizer(Organizer organizer) {
        return organizerList.add(organizer);
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
        for (Event event:
                this.waitingList) {
            if(event.getID().equals(idEvent)){
                return Optional.of(event);
            }
        }

        return Optional.empty();
    }

}
