package fr.unice.polytech.isa.teamk;

import fr.unice.polytech.isa.teamk.exceptions.AlreadyExistingOrganizer;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyLoggedInOrganizer;
import fr.unice.polytech.isa.teamk.exceptions.UnknownOrganizerException;

import javax.ejb.Local;

@Local
public interface OrganizerRegister {

    void registerOrganizer(String name, String email, String password, String phone) throws AlreadyExistingOrganizer;

    void loginOrganizer(String email, String password) throws UnknownOrganizerException, AlreadyLoggedInOrganizer;

}
