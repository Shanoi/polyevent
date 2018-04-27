package fr.unice.polytech.isa.teamk.services;

import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.entities.EventStatus;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyExistingEventException;
import fr.unice.polytech.isa.teamk.exceptions.ExternalPartnerException;
import fr.unice.polytech.isa.teamk.exceptions.RegisterEventException;
import fr.unice.polytech.isa.teamk.exceptions.UnknownEventException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.HashMap;
import java.util.List;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/event")
public interface EventService {

    @WebMethod
    void submitNewEvent(@WebParam(name = "event_name") String eventName,
                        @WebParam(name = "start_date") String startDate,
                        @WebParam(name = "end_date") String endDate,
                        @WebParam(name = "nb_attendee") int nbAttendee,
                        @WebParam(name = "organizer_email") String organizerEmail) throws RegisterEventException, AlreadyExistingEventException;

    @WebMethod
    List<Event> getSubmittedEvents();

    @WebMethod
    HashMap<String, EventStatus> getEventsByOrganizer(@WebParam(name = "organizer_email") String organizerEmail);

    @WebMethod
    boolean confirmEvent(@WebParam(name = "event_name") String eventName,
                         @WebParam(name = "rooms") List<String> rooms) throws RegisterEventException, UnknownEventException, ExternalPartnerException;

}
