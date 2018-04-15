package fr.unice.polytech.isa.teamk.exceptions;

import javax.xml.ws.WebFault;
import java.io.Serializable;

@WebFault(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/event")
public class RegisterEventException extends Exception implements Serializable {

    private String eventName;

    public RegisterEventException(String eventName, String roomID) {
        this.eventName = eventName;
    }

    public RegisterEventException(String eventName, Exception source) {
        super(source);
        this.eventName = eventName;
    }

    public RegisterEventException(String name) {
    }

    public String getName() {
        return eventName;
    }

    public void setName(String eventName) {
        this.eventName = eventName;
    }

}
