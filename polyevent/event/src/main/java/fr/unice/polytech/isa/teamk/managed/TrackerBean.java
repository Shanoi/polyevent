package fr.unice.polytech.isa.teamk.managed;

import fr.unice.polytech.isa.teamk.Tracker;
import fr.unice.polytech.isa.teamk.exceptions.UnknownEventException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean
public class TrackerBean {

    private static final Logger log = Logger.getLogger(TrackerBean.class.getName());
    @EJB
    private Tracker tracker;

    @ManagedProperty("#{param.eventName}")  // Will be automatically injected from the GET parameter
    private String eventName;
    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }

    @ManagedProperty("#{param.eventId}")  // Will be automatically injected from the GET parameter
    private String eventId;
    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }

    public String getStatus() {
        if(eventId == null) { return "No eventId given!"; }
        try {
            return tracker.status(eventId).name();
        } catch (UnknownEventException ueid) {
            log.log(Level.INFO, "Unknown eventId", ueid);
            return "Unknown Order [" + eventId + "]";
        }
    }

}
