package fr.unice.polytech.isa.teamk;

import fr.unice.polytech.isa.teamk.exceptions.AlreadyExistingOrganizer;

import javax.ejb.Local;

@Local
public interface OrganizerRegister {

    boolean registerOrganizer(String name, String id) throws AlreadyExistingOrganizer;

}