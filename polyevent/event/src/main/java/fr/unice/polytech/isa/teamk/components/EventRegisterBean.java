package fr.unice.polytech.isa.teamk.components;

import fr.unice.polytech.isa.teamk.EventRegister;
import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.exceptions.ExternalPartnerException;
import fr.unice.polytech.isa.teamk.exceptions.RegisterEventException;
import fr.unice.polytech.isa.teamk.exceptions.UncheckedException;
import fr.unice.polytech.isa.teamk.external.CalendarService;
import fr.unice.polytech.isa.teamk.utils.DatabaseSingletonEvent;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class EventRegisterBean implements EventRegister {

    private static final Logger log = Logger.getLogger(Logger.class.getName());

    private CalendarService calendarService;

    @EJB
    private DatabaseSingletonEvent databaseSingletonEvent;

    public EventRegisterBean() {

    }

    @Override
    public boolean registerEvent(Event event, String roomID) throws RegisterEventException {
        boolean status;
        try {
            status = calendarService.submitEvent(event, roomID);
        } catch (ExternalPartnerException e) {
            log.log(Level.INFO, "Error while exchanging with external partner", e);
            throw new RegisterEventException(event.getName(), roomID, e);
        }

        if (!status) {
            throw new RegisterEventException(event.getName(), roomID);
        }


        databaseSingletonEvent.validateEvent(event);

        //TODO PUT THE J2E SIDE CODE HERE

        return status;
    }

    @PostConstruct
    private void initializeRestPartnership() {
        try {
            Properties prop = new Properties();
            prop.load(this.getClass().getResourceAsStream("/calendar.properties"));
            calendarService = new CalendarService(prop.getProperty("calendarHostName"),
                    prop.getProperty("calendarPortNumber"));
        } catch (IOException e) {
            log.log(Level.INFO, "Cannot read calendar.properties file", e);
            throw new UncheckedException(e);
        }
    }

}
