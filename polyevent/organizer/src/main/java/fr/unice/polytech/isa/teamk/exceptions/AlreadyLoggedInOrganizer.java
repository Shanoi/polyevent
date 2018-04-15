package fr.unice.polytech.isa.teamk.exceptions;

import java.io.Serializable;

public class AlreadyLoggedInOrganizer extends Exception implements Serializable {

    private String email;

    public AlreadyLoggedInOrganizer(String email) {
        this.email = email;
    }

    public AlreadyLoggedInOrganizer() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
