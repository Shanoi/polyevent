package fr.unice.polytech.isa.teamk.services;

import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.exceptions.RegisterEventException;
import fr.unice.polytech.isa.teamk.exceptions.UnknownEventException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/responsible")
public interface ResponsibleService {

    @WebMethod
    void registerResponsible(@WebParam(name = "responsible_name") String name,
                             @WebParam(name = "responsible_id") String id);

    @WebMethod
    List<Event> getEventList();

    @WebMethod
    void validateEvent(@WebParam(name = "responsible_id") String responsibleID,
                       @WebParam(name = "event_id") String eventID,
                       @WebParam(name = "room_id") String roomID) throws RegisterEventException, UnknownEventException;

}
