package fr.unice.polytech.isa.teamk.exceptions;

import javax.xml.ws.WebFault;
import java.io.Serializable;

@WebFault(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/organizer")
public class AlreadyLoggedInResponsibleException extends Exception implements Serializable {

    private String email;

    public AlreadyLoggedInResponsibleException(String email) {
        this.email = email;
    }

    public AlreadyLoggedInResponsibleException() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
