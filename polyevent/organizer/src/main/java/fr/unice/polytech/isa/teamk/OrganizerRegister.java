package fr.unice.polytech.isa.teamk;

import fr.unice.polytech.isa.teamk.exceptions.AlreadyExistingOrganizer;

import javax.ejb.Local;

@Local
public interface OrganizerRegister {

    boolean registerOrganizer(String id, String password) throws AlreadyExistingOrganizer;

}