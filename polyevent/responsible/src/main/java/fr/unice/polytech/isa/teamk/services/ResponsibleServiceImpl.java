package fr.unice.polytech.isa.teamk.services;

import fr.unice.polytech.isa.teamk.EventFinder;
import fr.unice.polytech.isa.teamk.EventRegister;
import fr.unice.polytech.isa.teamk.ResponsibleRegister;
import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.entities.EventStatus;
import fr.unice.polytech.isa.teamk.exceptions.RegisterEventException;
import fr.unice.polytech.isa.teamk.exceptions.UnknownEventException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.List;
import java.util.Optional;

@Stateless(name = "ResponsibleWS")
@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/responsible")
public class ResponsibleServiceImpl implements ResponsibleService {

    @EJB
    private ResponsibleRegister responsibleRegister;

    @EJB
    private EventFinder finder;

    @EJB
    private EventRegister register;

    public ResponsibleServiceImpl() {

    }

    @Override
    public void registerResponsible(String name, String id) {

    }

    @Override
    public List<Event> getEventList() {
        return finder.searchEventByStatus(EventStatus.SUBMITTED);
    }

    @Override
    public void validateEvent(String responsibleID, String eventID, String roomID)
            throws RegisterEventException, UnknownEventException {
        Optional<Event> event = finder.searchEventByID(eventID);

        if (event.isPresent()) {
            register.confirmEvent(event.get());
        } else {
            throw new UnknownEventException(eventID);
        }
    }

}
