package fr.unice.polytech.isa.teamk.services;

import fr.unice.polytech.isa.teamk.EventModifier;
import fr.unice.polytech.isa.teamk.OrganizerFinder;
import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.entities.users.Organizer;
import fr.unice.polytech.isa.teamk.exceptions.UnknownOrganizerException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.Optional;

@Stateless(name = "EventWS")
@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/event")
public class EventServiceImpl implements EventService {

    @EJB
    private EventModifier eventTracker;

    @EJB
    private OrganizerFinder organizerFinder;

    public EventServiceImpl() {

    }

    @Override
    public void submitNewEvent(Event event) throws UnknownOrganizerException {
        Optional<Organizer> organizer = organizerFinder.searchOrganizerByID(event.getOrganizer().getId());

        if(organizer.isPresent()){
            eventTracker.forwardNewEvent(event);
        } else {
            throw new UnknownOrganizerException(event.getOrganizer().getId());
        }
    }

}