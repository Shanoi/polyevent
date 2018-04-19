package fr.unice.polytech.isa.teamk.exceptions;

import javax.xml.ws.WebFault;
import java.io.Serializable;

@WebFault(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/event")
public class AlreadyExistingEvent extends Exception implements Serializable {

    private String name;

    public AlreadyExistingEvent(String name) {
        this.name = name;
    }

    public AlreadyExistingEvent() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
