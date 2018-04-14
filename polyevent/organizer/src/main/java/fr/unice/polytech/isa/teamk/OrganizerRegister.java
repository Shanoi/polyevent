package fr.unice.polytech.isa.teamk;

import fr.unice.polytech.isa.teamk.exceptions.AlreadyExistingOrganizer;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyLoggedInOrganizer;
import fr.unice.polytech.isa.teamk.exceptions.UnknownOrganizerException;

import javax.ejb.Local;

@Local
public interface OrganizerRegister {

    boolean registerOrganizer(String id, String password) throws AlreadyExistingOrganizer;

    boolean loginOrganizer(String id, String password) throws UnknownOrganizerException, AlreadyLoggedInOrganizer;

}
