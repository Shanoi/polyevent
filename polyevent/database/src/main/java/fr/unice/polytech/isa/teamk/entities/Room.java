package fr.unice.polytech.isa.teamk.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Room implements Serializable {


    @OneToMany
    private Set<Material> materials = new HashSet<>();
    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
