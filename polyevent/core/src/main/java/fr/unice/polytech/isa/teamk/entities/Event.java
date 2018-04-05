package fr.unice.polytech.isa.teamk.entities;

import fr.unice.polytech.isa.teamk.entities.users.Organizer;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.GregorianCalendar;

@Embeddable
public class Event implements Serializable {

    @NotNull
    private String name;

    @NotNull
    private GregorianCalendar startingDate;

    @NotNull
    private GregorianCalendar endingDate;

    @NotNull
    private String id;

    @NotNull
    private Organizer organizer;

    public Event() {

    }

    public Event(String name, GregorianCalendar gregorianCalendar, GregorianCalendar calendar, Organizer id) {
        this.name = name;
        this.startingDate = gregorianCalendar;
        this.endingDate = calendar;
        this.organizer = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GregorianCalendar getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(GregorianCalendar startingDate) {
        this.startingDate = startingDate;
    }

    public GregorianCalendar getEndingDate() {
        return endingDate;
    }

    public int integrationGetter() {
        return 1;
    }

    public void setEndingDate(GregorianCalendar endingDate) {
        this.endingDate = endingDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

}