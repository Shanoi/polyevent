package fr.unice.polytech.isa.teamk.services;

import fr.unice.polytech.isa.teamk.OrganizerFinder;
import fr.unice.polytech.isa.teamk.components.EventModifierBean;
import fr.unice.polytech.isa.teamk.entities.Event;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

@Stateless(name = "EventWS")
@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/event")
public class EventServiceImpl implements EventService {

    @EJB
    private EventModifierBean eventModifier;

    @EJB
    private OrganizerFinder organizerFinder;

    public EventServiceImpl() {

    }

    @Override
    public void submitNewEvent(Event event) /*throws UnknownOrganizerException */{
        /*Optional<Organizer> organizer = organizerFinder.searchOrganizerByID(event.getOrganizer().getId()); On connait déjà l'organisateur

        if(organizer.isPresent()){
            eventModifier.forwardNewEvent(event);
        } else {
            throw new UnknownOrganizerException(event.getOrganizer().getId());
        }*/
        eventModifier.forwardNewEvent(event);


    }

}