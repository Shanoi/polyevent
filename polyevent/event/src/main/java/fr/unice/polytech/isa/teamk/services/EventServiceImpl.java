package fr.unice.polytech.isa.teamk.services;

import fr.unice.polytech.isa.teamk.OrganizerFinder;
import fr.unice.polytech.isa.teamk.components.EventRegistryBean;
import fr.unice.polytech.isa.teamk.entities.Event;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    public void submitNewEvent(String eventName, String startDate, String endDate, int nbAttendee, String organizerEmail) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        LocalDateTime startDateTime = LocalDateTime.parse(startDate, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endDate, formatter);
        eventRegistryBean.submitNewEvent(new Event(eventName, startDateTime, endDateTime, nbAttendee), organizerEmail);
    }

}
