package fr.unice.polytech.isa.teamk.services;

import fr.unice.polytech.isa.teamk.exceptions.AlreadyExistingOrganizer;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyLoggedInOrganizer;
import fr.unice.polytech.isa.teamk.exceptions.UnknownOrganizerException;
import fr.unice.polytech.isa.teamk.interceptors.PriceVerifier;

import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/organizer")
public interface OrganizerService {

    @WebMethod
    void registerOrganizer(@WebParam(name = "organizer_id") String id,
                           @WebParam(name = "organizer_password") String password) throws AlreadyExistingOrganizer;

    @WebMethod
    void loginOrganizer(@WebParam(name = "organizer_id") String id,
                       @WebParam(name = "organizer_password") String password) throws UnknownOrganizerException, AlreadyLoggedInOrganizer;

    @WebMethod
    @Interceptors({PriceVerifier.class})
    void sendPayment(@WebParam(name = "organizer_id") String id,
                     @WebParam(name = "price") int price);

}