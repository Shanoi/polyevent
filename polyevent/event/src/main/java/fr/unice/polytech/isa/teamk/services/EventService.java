package fr.unice.polytech.isa.teamk.services;

import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.exceptions.UnknownOrganizerException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.time.LocalDateTime;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/event")
public interface EventService {

    @WebMethod
    void submitNewEvent(@WebParam(name = "event") Event event) /*throws UnknownOrganizerException*/;

    /*void submitNewEvent(@WebParam(name = "event_name") String name,
                        @WebParam(name = "id_organizer") String id,
                        @WebParam(name = "start_date") LocalDateTime startDate,
                        @WebParam(name = "end_date") LocalDateTime endDate) throws UnknownOrganizerException;*/

}