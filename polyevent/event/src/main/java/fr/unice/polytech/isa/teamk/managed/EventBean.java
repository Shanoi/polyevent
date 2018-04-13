package fr.unice.polytech.isa.teamk.managed;

import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.exceptions.RegisterEventException;
import fr.unice.polytech.isa.teamk.external.CalendarService;
import fr.unice.polytech.isa.teamk.utils.DatabaseSingletonEvent;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@ManagedBean
@SessionScoped
public class EventBean implements Serializable {

    private static final Logger log = Logger.getLogger(Logger.class.getName());

    private CalendarService calendarService;

    @ManagedProperty("#{organizerBean.id}")
    private String customerId;

    @ManagedProperty("#{organizerBean.password}")
    private String customerPassword;

    private String name;
    private Date startDate;
    private Date endDate;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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

    @EJB
    private DatabaseSingletonEvent databaseSingletonBean;


    public String registerEvent() throws RegisterEventException {

        Event event = new Event(name,startDate,endDate,
                nbAttendee);

        databaseSingletonBean.registerEvent(event);

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
