package fr.unice.polytech.isa.teamk.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Organizer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organizer organizer = (Organizer) o;
        return Objects.equals(getName(), organizer.getName()) &&
                Objects.equals(getPassword(), organizer.getPassword()) &&
                Objects.equals(getEmail(), organizer.getEmail()) &&
                Objects.equals(getPhone(), organizer.getPhone());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getPassword(), getEmail(), getPhone());
    }
}
