package fr.unice.polytech.isa.teamk.managed;

import fr.unice.polytech.isa.teamk.OrganizerFinder;
import fr.unice.polytech.isa.teamk.components.EventRegistryBean;
import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.entities.Organizer;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyExistingEventException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean
@SessionScoped
public class EventBean implements Serializable {

    private static final Logger log = Logger.getLogger(EventBean.class.getName());

    @EJB
    private transient EventRegistryBean eventRegistry;
    @EJB
    private transient OrganizerFinder finder;

    @ManagedProperty("#{organizerBean.email}")
    private String email;

    private String name;
    private String startDate;
    private String endDate;
    private int nbAttendee;
    private List<String> partners;
    private List selPartners;

    private Organizer organizer;

    public EventBean() {
        partners = new ArrayList<>();
        partners.add("Chennai");
        partners.add("Bangalore");
        partners.add("Pune");
        partners.add("Delhi");
        partners.add("Mumbai");
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

    public List<String> getPartners() {
        return partners;
    }

    public void setPartners(List<String> partners) {
        this.partners = partners;
    }

    public List<String> getSelPartners() {
        return selPartners;
    }

    public void setSelPartners(List<String> selPartners) {
        this.selPartners = selPartners;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Initializing the organizer after the construction of the bean
    @PostConstruct
    private void loadCustomer() {
        finder.searchOrganizerByEmail(email).ifPresent(organizer -> this.organizer = organizer);
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
        return new ArrayList<>(eventRegistry.searchEventByOrganizer(getOrganizer().getEmail()));
    }

//    @PostConstruct
//    private void initializeRestPartnership() {
//
///*        partners = new ArrayList<>();
//
//        partners.add("P1");
//        partners.add("P2");
//        partners.add("P3");
//        partners.add("P4");
//        partners.add("P5");
//        partners.add("P6");*/
//
//
//
//        /*try {
//            Properties prop = new Properties();
//            prop.load(this.getClass().getResourceAsStream("/calendar.properties"));
//            calendarService = new CalendarService(prop.getProperty("calendarHostName"),
//                    prop.getProperty("calendarPortNumber"));
//        } catch (IOException e) {
//            log.log(Level.INFO, "Cannot read calendar.properties file", e);
//            throw new UncheckedException(e);
//        }*/
//    }


}
