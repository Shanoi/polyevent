package fr.unice.polytech.isa.teamk.entities.users;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Organizer extends User implements Serializable {

    public Organizer() {

    }

    public Organizer(String name, String id) {
        super(name, id);
    }

}