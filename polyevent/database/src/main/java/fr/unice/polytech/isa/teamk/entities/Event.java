package fr.unice.polytech.isa.teamk.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

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

}
