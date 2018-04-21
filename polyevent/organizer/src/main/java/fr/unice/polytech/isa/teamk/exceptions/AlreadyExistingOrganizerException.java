package fr.unice.polytech.isa.teamk.exceptions;

import javax.xml.ws.WebFault;
import java.io.Serializable;

@WebFault(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/organizer")
public class AlreadyExistingOrganizerException extends Exception implements Serializable {

    private String email;

    public AlreadyExistingOrganizerException(String email) {
        this.email = email;
    }

    public AlreadyExistingOrganizerException() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
