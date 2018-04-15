package fr.unice.polytech.isa.teamk.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Material implements Serializable {

    @NotNull
    private int quantity;

    @NotNull
    private String type;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return getQuantity() == material.getQuantity() &&
                Objects.equals(getType(), material.getType()) &&
                Objects.equals(getRoom(), material.getRoom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuantity(), getType(), getRoom());
    }

}
