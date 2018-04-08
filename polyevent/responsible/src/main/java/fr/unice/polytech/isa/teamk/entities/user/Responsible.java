package fr.unice.polytech.isa.teamk.entities.user;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Responsible extends User implements Serializable {


    public Responsible() {

    }

    public Responsible(String id, String password) {
        super(id, password);
    }



}