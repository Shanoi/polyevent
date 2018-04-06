package fr.unice.polytech.isa.teamk.entities;

import fr.unice.polytech.isa.teamk.entities.users.Organizer;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

@Embeddable
public class Event implements Serializable {

    @NotNull
    private String name;

    private String id;

    @NotNull
    private Date startingDate;

    @NotNull
    private Date endingDate;

    @NotNull
    private Organizer organizer;

    @NotNull
    private int nbAttendee;

    //private HashMap<TypeSalle, nb>

    public Event() {

    }

    public Event(String name, Date startingDate, Date endingDate, Organizer organizer, int nbAttendee) {
        this.name = name;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.organizer = organizer;
        this.nbAttendee = nbAttendee;
    }

    public int integrationGetter() {
        return 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

}