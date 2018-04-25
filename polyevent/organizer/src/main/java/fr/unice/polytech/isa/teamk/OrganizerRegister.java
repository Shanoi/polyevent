package fr.unice.polytech.isa.teamk;

import fr.unice.polytech.isa.teamk.exceptions.AlreadyExistingOrganizerException;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyLoggedInOrganizerException;
import fr.unice.polytech.isa.teamk.exceptions.UnknownOrganizerException;

import javax.ejb.Local;

@Local
public interface OrganizerRegister {

    void registerOrganizer(String name, String email, String password, String phone) throws AlreadyExistingOrganizerException;

    void loginOrganizer(String email, String password) throws UnknownOrganizerException, AlreadyLoggedInOrganizerException;

    void disconnectOrganizer(String email);

}
