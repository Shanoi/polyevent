package fr.unice.polytech.isa.teamk.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Quote implements Serializable {

    @NotNull
    private float price;

    @NotNull
    private String description;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    private Event event;

    public Quote() {
    }

    public Quote(float price, String description, Event event) {
        this.price = price;
        this.description = description;
        this.event = event;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quote quote = (Quote) o;
        return Float.compare(quote.getPrice(), getPrice()) == 0 &&
                Objects.equals(getDescription(), quote.getDescription()) &&
                Objects.equals(getId(), quote.getId()) &&
                Objects.equals(getEvent(), quote.getEvent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrice(), getDescription(), getId(), getEvent().hashCode());
    }

}
