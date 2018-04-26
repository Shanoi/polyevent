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

    @ManagedProperty("#{param.eventId}")  // Will be automatically injected from the GET parameter
    private String eventId;
    public String getOrderId() { return eventId; }
    public void setOrderId(String orderId) { this.eventId = orderId; }

    public String getStatus() {
        if(eventId == null) { return "No eventId given!"; }
        try {
            return tracker.status(eventId).name();
        } catch (UnknownEventException uoid) {
            log.log(Level.INFO, "Unknown eventId", uoid);
            return "Unknown Order [" + eventId + "]";
        }
    }

}
