package fr.unice.polytech.isa.teamk.exceptions;

import java.io.Serializable;

public class AlreadyLoggedInOrganizer extends Exception implements Serializable {

    private String id;

    public AlreadyLoggedInOrganizer(String id) {
        this.id = id;
    }

    public AlreadyLoggedInOrganizer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
