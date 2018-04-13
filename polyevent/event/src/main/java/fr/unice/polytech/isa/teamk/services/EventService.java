package fr.unice.polytech.isa.teamk.services;

import fr.unice.polytech.isa.teamk.entities.Event;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/event")
public interface EventService {

    @WebMethod
    void submitNewEvent(@WebParam(name = "event") Event event, String organizerID);

}