package fr.unice.polytech.isa.teamk.components;

import fr.unice.polytech.isa.teamk.EventFinder;
import fr.unice.polytech.isa.teamk.EventRegister;
import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.exceptions.ExternalPartnerException;
import fr.unice.polytech.isa.teamk.exceptions.RegisterEventException;
import fr.unice.polytech.isa.teamk.external.CalendarService;
import org.apache.cxf.common.i18n.UncheckedException;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class EventRegistryBean implements EventRegister, EventFinder {

    private static final Logger log = Logger.getLogger(Logger.class.getName());

    @PersistenceContext
    private EntityManager manager;

    private CalendarService calendarService;

    @Override
    public boolean submitNewEvent(Event event) {
        return false;
    }

    @Override
    public boolean registerEvent(Event event) throws RegisterEventException {
        boolean status;

        try {
            status = calendarService.submitEvent(event);
        } catch (ExternalPartnerException e) {
            log.log(Level.INFO, "Error while exchanging with external partner", e);
            throw new RegisterEventException(event.getName(), e);
        }

        if (!status) {
            throw new RegisterEventException(event.getName());
        }

        manager.persist(event);

        //TODO PUT THE J2E SIDE CODE HERE

        return status;
    }

    @Override
    public Optional<Event> searchEventByID(String id) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Event> criteria = builder.createQuery(Event.class);
        Root<Event> root = criteria.from(Event.class);
        criteria.select(root).where(builder.equal(root.get("id"), id));
        TypedQuery<Event> query = manager.createQuery(criteria);

        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException nre) {
            log.log(Level.FINEST, "No result for [" + id + "]", nre);
            return Optional.empty();
        }
    }

    @Override
    public List<Event> getSubmittedEvents() {
        return null;
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
