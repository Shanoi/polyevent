package fr.unice.polytech.isa.teamk.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Material implements Serializable {

    @NotNull
    private int quantity;

    @NotNull
    private String type;

    @GeneratedValue
    private String id;

    @Id
    public String getId() {
        return id;
    }

    @ManyToOne
    private Room room;

    public void setId(String id) {
        this.id = id;
    }

    public Material(int quantity, String type) {
        this.quantity = quantity;
        this.type = type;
    }

    public Material() {
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
