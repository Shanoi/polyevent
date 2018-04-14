package fr.unice.polytech.isa.teamk.services;

import fr.unice.polytech.isa.teamk.OrganizerFinder;
import fr.unice.polytech.isa.teamk.components.EventRegistryBean;
import fr.unice.polytech.isa.teamk.entities.Event;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

@Stateless(name = "EventWS")
@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/event")
public class EventServiceImpl implements EventService {

    @EJB
    private EventRegistryBean eventRegistryBean;

    @EJB
    private OrganizerFinder organizerFinder;

    public EventServiceImpl() {

    }

    @Override
    public void submitNewEvent(Event event, String organizerID) {
        eventRegistryBean.submitNewEvent(event);
    }

}
