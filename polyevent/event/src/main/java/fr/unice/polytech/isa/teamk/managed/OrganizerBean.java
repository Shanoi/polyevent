package fr.unice.polytech.isa.teamk.managed;

import fr.unice.polytech.isa.teamk.OrganizerFinder;
import fr.unice.polytech.isa.teamk.OrganizerRegister;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyExistingOrganizer;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;


@ManagedBean
@SessionScoped
public class OrganizerBean implements Serializable {

    @EJB
    private transient OrganizerFinder finder;
    @EJB
    private transient OrganizerRegister registry;

    private static final Logger log = Logger.getLogger(OrganizerBean.class.getName());

    private String id;
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Invoked when the "Select" button is pushed
    public String connect() {
        if (finder.searchOrganizerByID(getId()).isPresent()) {
            return Signal.CONNECTED;
        } else {
            FacesContext.getCurrentInstance()
                    .addMessage("form-error", new FacesMessage("Unknown organizer: " + getId()));
            return Signal.UNKOWN;
        }
    }

    // Invoked when the "Register" button is pushed
    public String register() {
        try {
            registry.registerOrganizer(getId(), getPassword());
            return Signal.ADDED;
        } catch (AlreadyExistingOrganizer e) {
            log.log(Level.WARNING, "Unknown customer", e);
            FacesContext.getCurrentInstance()
                    .addMessage("form-error", new FacesMessage("Organizer " + getId() + " already exists!"));
            return Signal.EXISTING;
        }
    }

}

