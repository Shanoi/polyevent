package fr.unice.polytech.isa.teamk.services;

import fr.unice.polytech.isa.teamk.exceptions.UnknownOrganizerException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/event")
public interface EventService {

    @WebMethod
    void submitNewEvent(@WebParam(name = "event_name") String name,
                        @WebParam(name = "id_organizer") String id,
                        @WebParam(name = "start_day") short startDay,
                        @WebParam(name = "start_month") short startMonth,
                        @WebParam(name = "start_year") short startYear,
                        @WebParam(name = "end_day") short endDay,
                        @WebParam(name = "end_month") short endMonth,
                        @WebParam(name = "end_year") short endYear) throws UnknownOrganizerException;

}