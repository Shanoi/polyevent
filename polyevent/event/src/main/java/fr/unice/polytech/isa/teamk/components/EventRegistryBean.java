package fr.unice.polytech.isa.teamk.components;

import fr.unice.polytech.isa.teamk.EventFinder;
import fr.unice.polytech.isa.teamk.EventRegister;
import fr.unice.polytech.isa.teamk.OrganizerFinder;
import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.entities.EventStatus;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyExistingEvent;
import fr.unice.polytech.isa.teamk.exceptions.ExternalPartnerException;
import fr.unice.polytech.isa.teamk.exceptions.RegisterEventException;
import fr.unice.polytech.isa.teamk.external.CalendarService;
import org.apache.cxf.common.i18n.UncheckedException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    @EJB
    private OrganizerFinder organizerFinder;

    private CalendarService calendarService;

    @Override
    public void submitNewEvent(String eventName, String startDate, String endDate, int nbAttendee, String organizerEmail) throws AlreadyExistingEvent {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm d/M/yyyy");
        LocalDateTime startDateTime = LocalDateTime.parse(startDate, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endDate, formatter);

        Event event = new Event(eventName, Timestamp.valueOf(startDateTime), Timestamp.valueOf(endDateTime), nbAttendee);

        if (searchEventByName(event.getName()).isPresent()) {
            throw new AlreadyExistingEvent(event.getName());
        }

        // No need to check if the organizer exists because he's logged in.
        // In consequences, he exists.
        event.setOrganizer(organizerFinder.searchOrganizerByEmail(organizerEmail).get());

        manager.persist(event);
    }

    @Override
    public boolean confirmEvent(Event event) throws RegisterEventException {
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
    public Optional<Event> searchEventByName(String name) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Event> criteria = builder.createQuery(Event.class);
        Root<Event> root = criteria.from(Event.class);
        criteria.select(root).where(builder.equal(root.get("name"), name));
        TypedQuery<Event> query = manager.createQuery(criteria);

        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException nre) {
            log.log(Level.FINEST, "No result for [" + name + "]", nre);
            return Optional.empty();
        }
    }

    @Override
    public List<Event> searchEventByStatus(EventStatus status) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Event> criteria = builder.createQuery(Event.class);
        Root<Event> root = criteria.from(Event.class);
        criteria.select(root).where(builder.equal(root.get("status"), status));
        TypedQuery<Event> query = manager.createQuery(criteria);

        try {
            return query.getResultList();
        } catch (NoResultException nre) {
            log.log(Level.FINEST, "No result for [" + status + "]", nre);
            return new ArrayList<>();
        }
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
