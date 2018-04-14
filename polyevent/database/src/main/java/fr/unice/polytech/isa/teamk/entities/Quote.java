package fr.unice.polytech.isa.teamk.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Quote implements Serializable {

    @NotNull
    private float price;

    @NotNull
    private String description;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
