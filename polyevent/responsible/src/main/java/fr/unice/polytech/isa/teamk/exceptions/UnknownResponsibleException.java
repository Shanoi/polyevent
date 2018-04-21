package fr.unice.polytech.isa.teamk.exceptions;

import javax.xml.ws.WebFault;
import java.io.Serializable;

@WebFault(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/responsible")
public class UnknownResponsibleException extends Exception implements Serializable {

    private String email;

    public UnknownResponsibleException(String email) {
        this.email = email;
    }

    public UnknownResponsibleException() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String name) {
        this.email = name;
    }

}
