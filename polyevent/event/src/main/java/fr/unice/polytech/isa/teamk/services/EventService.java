package fr.unice.polytech.isa.teamk.services;

import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyExistingEvent;
import fr.unice.polytech.isa.teamk.exceptions.RegisterEventException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/event")
public interface EventService {

    @WebMethod
    void submitNewEvent(@WebParam(name = "event_name") String eventName,
                        @WebParam(name = "start_date") String startDate,
                        @WebParam(name = "end_date") String endDate,
                        @WebParam(name = "nb_attendee") int nbAttendee,
                        @WebParam(name = "organizer_email") String organizerEmail) throws RegisterEventException, AlreadyExistingEvent;

    @WebMethod
    List<Event> getSubmittedEvents();

}
