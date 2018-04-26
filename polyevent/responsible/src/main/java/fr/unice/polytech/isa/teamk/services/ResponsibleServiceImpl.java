package fr.unice.polytech.isa.teamk.services;

import fr.unice.polytech.isa.teamk.ResponsibleRegister;
import fr.unice.polytech.isa.teamk.entities.Room;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyExistingResponsibleException;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyLoggedInResponsibleException;
import fr.unice.polytech.isa.teamk.exceptions.ExternalPartnerException;
import fr.unice.polytech.isa.teamk.exceptions.UnknownResponsibleException;
import fr.unice.polytech.isa.teamk.external.CalendarService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless(name = "ResponsibleWS")
@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/responsible")
public class ResponsibleServiceImpl implements ResponsibleService {

    @EJB
    private ResponsibleRegister responsibleRegister;

    private CalendarService calendarService;

    private static final Logger log = Logger.getLogger(Logger.class.getName());

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
    public HashMap<String, Room[]> getVacantRooms(String startDate, String endDate) throws ExternalPartnerException {
        return calendarService.getVacantRooms(startDate, endDate);
    }

    @PostConstruct
    private void initializeRestPartnership() throws ExternalPartnerException {
        try {
            Properties prop = new Properties();
            prop.load(this.getClass().getResourceAsStream("/calendar.properties"));
            calendarService = new CalendarService(prop.getProperty("calendarHostName"),
                    prop.getProperty("calendarPortNumber"));
        } catch (IOException e) {
            log.log(Level.INFO, "Cannot read calendar.properties file", e);
            throw new ExternalPartnerException("Cannot read calendar.properties file", e);
        }
    }

}
