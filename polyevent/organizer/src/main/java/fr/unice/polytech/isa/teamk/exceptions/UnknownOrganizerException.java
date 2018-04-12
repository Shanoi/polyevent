package fr.unice.polytech.isa.teamk.exceptions;

import javax.xml.ws.WebFault;
import java.io.Serializable;

@WebFault(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/event")
public class UnknownOrganizerException extends Exception implements Serializable {

    private String id;

    public UnknownOrganizerException(String id) {
        this.id = id;
    }

    public UnknownOrganizerException() {
    }

    public String getID() {
        return id;
    }

    public void setID(String name) {
        this.id = name;
    }

}
