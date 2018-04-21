package fr.unice.polytech.isa.teamk.services;

import fr.unice.polytech.isa.teamk.EventFinder;
import fr.unice.polytech.isa.teamk.EventRegister;
import fr.unice.polytech.isa.teamk.OrganizerFinder;
import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.entities.EventStatus;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyExistingEventException;
import fr.unice.polytech.isa.teamk.exceptions.RegisterEventException;
import fr.unice.polytech.isa.teamk.exceptions.UnknownEventException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.List;

@Stateless(name = "EventWS")
@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/event")
public class EventServiceImpl implements EventService {

    @EJB
    private EventRegister eventRegister;

    @EJB
    private EventFinder eventFinder;

    @EJB
    private OrganizerFinder organizerFinder;

    public EventServiceImpl() {

    }

    @Override
    public void submitNewEvent(String eventName, String startDate, String endDate, int nbAttendee, String organizerEmail) throws AlreadyExistingEventException {
        eventRegister.submitNewEvent(eventName, startDate, endDate, nbAttendee, organizerEmail);
    }

    @Override
    public List<Event> getSubmittedEvents() {
        return eventFinder.searchEventByStatus(EventStatus.SUBMITTED);
    }

    @Override
    public boolean confirmEvent(String eventName, List<String> rooms) throws RegisterEventException, UnknownEventException {
        return eventRegister.confirmEvent(eventName, rooms);
    }

}
