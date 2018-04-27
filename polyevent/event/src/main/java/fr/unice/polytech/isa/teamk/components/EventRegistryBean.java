package fr.unice.polytech.isa.teamk.components;

import fr.unice.polytech.isa.teamk.EventFinder;
import fr.unice.polytech.isa.teamk.EventRegister;
import fr.unice.polytech.isa.teamk.OrganizerFinder;
import fr.unice.polytech.isa.teamk.Tracker;
import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.entities.EventStatus;
import fr.unice.polytech.isa.teamk.entities.Organizer;
import fr.unice.polytech.isa.teamk.entities.Room;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyExistingEventException;
import fr.unice.polytech.isa.teamk.exceptions.ExternalPartnerException;
import fr.unice.polytech.isa.teamk.exceptions.RegisterEventException;
import fr.unice.polytech.isa.teamk.exceptions.UnknownEventException;
import fr.unice.polytech.isa.teamk.external.CalendarService;
import fr.unice.polytech.isa.teamk.interceptors.TimeVerifier;
import org.apache.cxf.common.i18n.UncheckedException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
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
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static fr.unice.polytech.isa.teamk.entities.EventStatus.next;

@Stateless
public class EventRegistryBean implements EventRegister, EventFinder, Tracker {

    private static final Logger log = Logger.getLogger(Logger.class.getName());

    @PersistenceContext
    private EntityManager manager;

    @EJB
    private OrganizerFinder organizerFinder;

    private CalendarService calendarService;

    @Override
    @Interceptors(TimeVerifier.class)
    public String submitNewEvent(String eventName, String startDate, String endDate, int nbAttendee, String organizerEmail) throws AlreadyExistingEventException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm d/M/yyyy");
        LocalDateTime startDateTime = LocalDateTime.parse(startDate, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endDate, formatter);

        Event event = new Event(eventName, Timestamp.valueOf(startDateTime), Timestamp.valueOf(endDateTime), nbAttendee);

        if (searchEventByName(event.getName()).isPresent()) {
            throw new AlreadyExistingEventException(event.getName());
        }

        // No need to check if the organizer exists because he's logged in.
        // In consequences, he exists.
        event.setOrganizer(organizerFinder.searchOrganizerByEmail(organizerEmail).get());

        manager.persist(event);

        return Integer.toString(event.getId());

    }

    @Override
    public boolean confirmEvent(String eventName, List<String> rooms) throws RegisterEventException, UnknownEventException, ExternalPartnerException {
        Optional<Event> event = searchEventByName(eventName);

        if (event.isPresent()) {
            boolean status;

            try {
                status = calendarService.confirmEvent(event.get(), rooms);
            } catch (ExternalPartnerException e) {
                log.log(Level.INFO, "Error while exchanging with external partner", e);
                throw new RegisterEventException(event.get().getName(), e);
            }

            if (!status) {
                throw new RegisterEventException(event.get().getName());
            }

            List<Room> roomList = new ArrayList<>();

            for (String roomID : rooms) {
                roomList.add(calendarService.roomInfo(roomID));
            }

            event.get().setRooms(roomList);
            Optional<EventStatus> next = EventStatus.next(event.get().getStatus());
            if(next.isPresent()) {
                log.log(Level.INFO, "Moving event [" + event.get().getId() + ", " + event.get().getName() + "] to next step");
                event.get().setStatus(next.get());
            } else {
                log.log(Level.INFO, "No more scheduled operation for Event [" +
                        event.get().getId() + ", " + event.get().getName() + "]");
            }

            return true;
        } else {
            throw new UnknownEventException(eventName);
        }
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

        /*
        // Other solution
        List<Event> events = manager.createQuery("SELECT e FROM Event e", Event.class).getResultList();

        for (Event e : events) {
            if (e.getStatus() != status) {
                events.remove(e);
            }
        }

        return events;
        */

        try {
            return query.getResultList();
        } catch (NoResultException nre) {
            log.log(Level.FINEST, "No result for [" + status + "]", nre);
            return new ArrayList<>();
        }
    }

    @Override
    public Set<Event> searchEventByOrganizer(String email) {
        Optional<Organizer> organizer = organizerFinder.searchOrganizerByEmail(email);

        // Safe check, but organizer should always exists since it has be logged in to use commands
        // that call this method
        if (organizer.isPresent()) {
            Organizer o = manager.merge(organizer.get());
            return o.getEvents();
        }

        return new HashSet<>();
    }

    @Override
    public EventStatus status(String eventId) throws UnknownEventException {
        Event event = manager.find(Event.class, Integer.parseInt(eventId));
        if (event == null)
            throw new UnknownEventException(eventId);
        return event.getStatus();
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
