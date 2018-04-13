package fr.unice.polytech.isa.teamk.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Quote implements Serializable {

    @NotNull
    private float price;

    @NotNull
    private String description;

    @Id
    @GeneratedValue
    private String id;

    @OneToOne
    private Event event;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
