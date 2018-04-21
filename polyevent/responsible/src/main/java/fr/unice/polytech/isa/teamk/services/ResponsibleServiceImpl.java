package fr.unice.polytech.isa.teamk.services;

import fr.unice.polytech.isa.teamk.EventFinder;
import fr.unice.polytech.isa.teamk.EventRegister;
import fr.unice.polytech.isa.teamk.ResponsibleRegister;
import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.exceptions.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.List;

@Stateless(name = "ResponsibleWS")
@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/responsible")
public class ResponsibleServiceImpl implements ResponsibleService {

    @EJB
    private ResponsibleRegister responsibleRegister;

    @EJB
    private EventFinder eventFinder;

    @EJB
    private EventRegister eventRegister;

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

    @Override
    public void confirmEvent(String eventName, List<String> rooms) throws RegisterEventException, UnknownEventException {
        eventRegister.confirmEvent(eventName, rooms);
    }

}
