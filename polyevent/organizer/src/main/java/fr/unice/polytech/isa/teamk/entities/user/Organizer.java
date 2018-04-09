package fr.unice.polytech.isa.teamk.entities.user;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Organizer extends User implements Serializable {

    public Organizer() {

    }

    public Organizer(String id, String password) {
        super(id, password);
    }

}