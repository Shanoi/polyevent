package fr.unice.polytech.isa.teamk.entities.user;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Organizer extends User implements Serializable {


    public Organizer() {

    }

    public Organizer(String id, String password) {
        super(id, password);
    }

}