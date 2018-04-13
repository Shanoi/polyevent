package fr.unice.polytech.isa.teamk.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Provider implements Serializable {

    @NotNull
    private String name;

    @NotNull
    private String adress;

    @NotNull
    private String phone;

    @NotNull
    private String mail;

    @NotNull
    private String type;

    @NotNull
    private float price;

    @ManyToOne
    private Event event;

    public Provider() {
    }

    private String id;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
