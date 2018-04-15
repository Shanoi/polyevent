package fr.unice.polytech.isa.teamk.managed;

import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.exceptions.RegisterEventException;
import fr.unice.polytech.isa.teamk.external.CalendarService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@ManagedBean
@SessionScoped
public class EventBean implements Serializable {

    private static final Logger log = Logger.getLogger(Logger.class.getName());

    private CalendarService calendarService;

    @ManagedProperty("#{organizerBean.email}")
    private String email;

    @ManagedProperty("#{organizerBean.password}")
    private String password;

    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
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

    @PersistenceContext
    private EntityManager manager;

    public String registerEvent() throws RegisterEventException {
        Event event = new Event(name, startDate, endDate, nbAttendee);
        manager.persist(event);

        return Signal.ADDED;
    }

    @PostConstruct
    private void initializeRestPartnership() {

        partners = new ArrayList<>();

        partners.add("P1");
        partners.add("P2");
        partners.add("P3");
        partners.add("P4");
        partners.add("P5");
        partners.add("P6");

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
