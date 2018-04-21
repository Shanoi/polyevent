package fr.unice.polytech.isa.teamk.services;

import fr.unice.polytech.isa.teamk.exceptions.AlreadyExistingResponsibleException;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyLoggedInResponsibleException;
import fr.unice.polytech.isa.teamk.exceptions.UnknownResponsibleException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/responsible")
public interface ResponsibleService {

    @WebMethod
    void registerResponsible(@WebParam(name = "responsible_name") String name,
                             @WebParam(name = "responsible_email") String email,
                             @WebParam(name = "responsible_password") String password,
                             @WebParam(name = "responsible_phone") String phone) throws AlreadyExistingResponsibleException;

    @WebMethod
    void loginResponsible(@WebParam(name = "responsible_email") String email,
                          @WebParam(name = "responsible_password") String password) throws AlreadyLoggedInResponsibleException, UnknownResponsibleException;

}
