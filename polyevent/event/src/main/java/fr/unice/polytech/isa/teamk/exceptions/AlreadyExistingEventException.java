package fr.unice.polytech.isa.teamk.exceptions;

import javax.xml.ws.WebFault;
import java.io.Serializable;

@WebFault(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/event")
public class AlreadyExistingEventException extends Exception implements Serializable {

    private String name;

    public AlreadyExistingEventException(String name) {
        this.name = name;
    }

    public AlreadyExistingEventException() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
