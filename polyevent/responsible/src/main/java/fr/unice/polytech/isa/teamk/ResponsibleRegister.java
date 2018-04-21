package fr.unice.polytech.isa.teamk;

import fr.unice.polytech.isa.teamk.exceptions.AlreadyExistingResponsibleException;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyLoggedInResponsibleException;
import fr.unice.polytech.isa.teamk.exceptions.UnknownResponsibleException;

import javax.ejb.Local;

@Local
public interface ResponsibleRegister {

    void registerResponsible(String name, String email, String password, String phone) throws AlreadyExistingResponsibleException;

    void loginResponsible(String email, String password) throws UnknownResponsibleException, AlreadyLoggedInResponsibleException;

}
