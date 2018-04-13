package fr.unice.polytech.isa.teamk.exceptions;

import javax.xml.ws.WebFault;
import java.io.Serializable;

@WebFault(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/organizer")
public class AlreadyExistingOrganizer extends Exception implements Serializable {

    private String id;

    public AlreadyExistingOrganizer(String id) {
        this.id = id;
    }

    public AlreadyExistingOrganizer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
