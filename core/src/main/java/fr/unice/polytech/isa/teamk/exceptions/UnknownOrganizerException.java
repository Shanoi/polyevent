package fr.unice.polytech.isa.teamk.exceptions;


import javax.xml.ws.WebFault;
import java.io.Serializable;

@WebFault(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/event")
public class UnknownOrganizerException extends Exception implements Serializable {

    private String name;

    public UnknownOrganizerException(String name) {
        this.name = name;
    }


    public UnknownOrganizerException() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
