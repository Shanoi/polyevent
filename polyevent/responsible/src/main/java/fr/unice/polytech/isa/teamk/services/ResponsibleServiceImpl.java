package fr.unice.polytech.isa.teamk.services;

import fr.unice.polytech.isa.teamk.ResponsibleRegister;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyExistingResponsibleException;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyLoggedInResponsibleException;
import fr.unice.polytech.isa.teamk.exceptions.UnknownResponsibleException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

@Stateless(name = "ResponsibleWS")
@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/responsible")
public class ResponsibleServiceImpl implements ResponsibleService {

    @EJB
    private ResponsibleRegister responsibleRegister;

    public ResponsibleServiceImpl() {

    }

    @Override
    public void registerResponsible(String name, String email, String password, String phone) throws AlreadyExistingResponsibleException {
        responsibleRegister.registerResponsible(name, email, password, phone);
    }

    @Override
    public void loginResponsible(String email, String password) throws UnknownResponsibleException, AlreadyLoggedInResponsibleException {
        responsibleRegister.loginResponsible(email, password);
    }

}
