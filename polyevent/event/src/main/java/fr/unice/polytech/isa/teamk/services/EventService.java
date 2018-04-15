package fr.unice.polytech.isa.teamk.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/event")
public interface EventService {

    @WebMethod
    void submitNewEvent(@WebParam(name = "event_name") String eventName,
                        @WebParam(name = "start_date") String startDate,
                        @WebParam(name = "end_date") String endDate,
                        @WebParam(name = "nb_attendee") int nbAttendee,
                        @WebParam(name = "organizer_email") String organizerEmail);

}
