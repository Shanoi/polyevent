package fr.unice.polytech.isa.teamk.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Organizer implements Serializable {

    @Id
    @GeneratedValue
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String password;

    @NotNull
    private String email;

    @NotNull
    private String phone;

    @OneToMany(mappedBy = "organizer")
    private Set<Event> events = new HashSet<>();

    public Organizer() {
    }

    public Organizer(String name, String password, String email, String phone, Set<Event> events) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.events = events;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

}
