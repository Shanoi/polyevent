package fr.unice.polytech.isa.teamk.exceptions;

import javax.xml.ws.WebFault;
import java.io.Serializable;


@WebFault(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/responsible")
public class UnknownEventException extends Exception implements Serializable {

    private String name;

    public UnknownEventException(String name) {
        this.name = name;
    }


    public UnknownEventException() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
