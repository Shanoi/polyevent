package fr.unice.polytech.isa.teamk.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String name;

    @Temporal(TemporalType.DATE)
    private Date startingDate;

    @Temporal(TemporalType.DATE)
    private Date endingDate;

    @ManyToOne
    private Organizer organizer;

    @NotNull
    private int nbAttendee;

    @OneToMany
    private Set<Provider> providers = new HashSet<>();

    @OneToOne
    private Quote quote;

    //private List<Room> rooms;

    public Event() {

    }

    public Event(String name, Date startingDate, Date endingDate, int nbAttendee) {
        this.name = name;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.nbAttendee = nbAttendee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getNbAttendee() {
        return nbAttendee;
    }

    public void setNbAttendee(int nbAttendee) {
        this.nbAttendee = nbAttendee;
    }

    public Set<Provider> getProviders() {
        return providers;
    }

    public void setProviders(Set<Provider> providers) {
        this.providers = providers;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return getNbAttendee() == event.getNbAttendee() &&
                Objects.equals(getName(), event.getName()) &&
                Objects.equals(getStartingDate(), event.getStartingDate()) &&
                Objects.equals(getEndingDate(), event.getEndingDate()) &&
                Objects.equals(getOrganizer(), event.getOrganizer());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getStartingDate(), getEndingDate(), getOrganizer(), getNbAttendee());
    }
}
