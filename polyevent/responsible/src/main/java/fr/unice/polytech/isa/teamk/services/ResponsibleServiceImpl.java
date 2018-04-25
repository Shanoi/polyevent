package fr.unice.polytech.isa.teamk.services;

import fr.unice.polytech.isa.teamk.ResponsibleRegister;
import fr.unice.polytech.isa.teamk.entities.Room;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyExistingResponsibleException;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyLoggedInResponsibleException;
import fr.unice.polytech.isa.teamk.exceptions.ExternalPartnerException;
import fr.unice.polytech.isa.teamk.exceptions.UnknownResponsibleException;
import fr.unice.polytech.isa.teamk.external.CalendarService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Stateless(name = "ResponsibleWS")
@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/responsible")
public class ResponsibleServiceImpl implements ResponsibleService {

    @EJB
    private ResponsibleRegister responsibleRegister;

    private CalendarService calendarService;

    public ResponsibleServiceImpl() {

    }

    @Override
    public void registerResponsible(String name, String email, String password, String phone) throws AlreadyExistingResponsibleException {
        responsibleRegister.registerResponsible(name, email, password, phone);
    }

    @Override
    public void loginResponsible(String email, String password) throws UnknownResponsibleException, AlreadyLoggedInResponsibleException {
        responsibleRegister.loginResponsible(email, password);
    }

    @Override
    public void disconnectResponsible(String email) {
        responsibleRegister.disconnectResponsible(email);
    }

    @Override
    public LinkedHashMap<String, ArrayList<Room>> getVacantRooms(String startDate, String endDate) throws ExternalPartnerException {
        return (LinkedHashMap<String, ArrayList<Room>>) calendarService.getVacantRooms(startDate, endDate);
    }

}
