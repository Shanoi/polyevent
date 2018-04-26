package fr.unice.polytech.isa.teamk.managed;

import fr.unice.polytech.isa.teamk.OrganizerFinder;
import fr.unice.polytech.isa.teamk.components.EventRegistryBean;
import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.entities.Organizer;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyExistingEventException;
import fr.unice.polytech.isa.teamk.exceptions.RegisterEventException;
import fr.unice.polytech.isa.teamk.external.CalendarService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean
@SessionScoped
public class EventBean implements Serializable {

    private static final Logger log = Logger.getLogger(Logger.class.getName());

    private CalendarService calendarService;

    @ManagedProperty("#{organizerBean.email}")
    private String email;

    @PersistenceContext
    private EntityManager manager;

    @EJB
    private transient OrganizerFinder finder;
    @EJB
    private transient EventRegistryBean eventRegistry;

    private int eventId;

    private Organizer organizer;
    private String name;
    private String startDate;
    private String endDate;
    private int nbAttendee;
    private List<String> selectedPartners;

    private List<String> partners;

    public List<String> getPartners() {
        return partners;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getNbAttendee() {
        return nbAttendee;
    }

    public void setNbAttendee(int nbAttendee) {
        this.nbAttendee = nbAttendee;
    }

    public List<String> getSelectedPartners() {
        return selectedPartners;
    }

    public void setSelectedPartners(List<String> selectedPartners) {
        this.selectedPartners = selectedPartners;
    }

    public void setPartners(List<String> partners) {
        this.partners = partners;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String registerEvent() {
       /* Event event = new Event(name, Timestamp.valueOf(startDate), Timestamp.valueOf(endDate), nbAttendee);
        manager.persist(event);*/

        try {
            eventRegistry.submitNewEvent(name, startDate, endDate, nbAttendee, email);
            return Signal.ADDED;
        } catch (AlreadyExistingEventException e) {
            log.log(Level.WARNING, "Event Already error", e);
            FacesContext.getCurrentInstance().addMessage("event-error", new FacesMessage("Event Already Existing!"));
            return Signal.EXISTING;
        }

    }

    /***************************
     ** Virtual Data bindings **
     ***************************/

    public List<Event> getEvents() {
        return new ArrayList<Event>(eventRegistry.searchEventByOrganizer(getOrganizer()));
    }

    @PostConstruct
    private void initializeRestPartnership() {

/*        partners = new ArrayList<>();

        partners.add("P1");
        partners.add("P2");
        partners.add("P3");
        partners.add("P4");
        partners.add("P5");
        partners.add("P6");*/

        finder.searchOrganizerByEmail(email).ifPresent(organizer -> this.organizer = organizer);

        /*try {
            Properties prop = new Properties();
            prop.load(this.getClass().getResourceAsStream("/calendar.properties"));
            calendarService = new CalendarService(prop.getProperty("calendarHostName"),
                    prop.getProperty("calendarPortNumber"));
        } catch (IOException e) {
            log.log(Level.INFO, "Cannot read calendar.properties file", e);
            throw new UncheckedException(e);
        }*/
    }

}
