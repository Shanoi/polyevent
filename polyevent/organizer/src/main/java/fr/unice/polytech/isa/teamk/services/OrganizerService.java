package fr.unice.polytech.isa.teamk.services;

import fr.unice.polytech.isa.teamk.exceptions.AlreadyExistingOrganizerException;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyLoggedInOrganizerException;
import fr.unice.polytech.isa.teamk.exceptions.UnknownOrganizerException;
import fr.unice.polytech.isa.teamk.interceptors.PriceVerifier;

import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/organizer")
public interface OrganizerService {

    @WebMethod
    void registerOrganizer(@WebParam(name = "organizer_name") String name,
                           @WebParam(name = "organizer_email") String email,
                           @WebParam(name = "organizer_password") String password,
                           @WebParam(name = "organizer_phone") String phone) throws AlreadyExistingOrganizerException;

    @WebMethod
    void loginOrganizer(@WebParam(name = "organizer_email") String email,
                        @WebParam(name = "organizer_password") String password) throws UnknownOrganizerException, AlreadyLoggedInOrganizerException;

    @WebMethod
    @Interceptors({PriceVerifier.class})
    void sendPayment(@WebParam(name = "organizer_id") String id,
                     @WebParam(name = "price") int price);

}
