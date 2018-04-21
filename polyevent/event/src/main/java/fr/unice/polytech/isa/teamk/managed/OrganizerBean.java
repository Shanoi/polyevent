package fr.unice.polytech.isa.teamk.managed;

import fr.unice.polytech.isa.teamk.OrganizerFinder;
import fr.unice.polytech.isa.teamk.OrganizerRegister;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyExistingOrganizerException;

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

    private String name;
    private String email;
    private String password;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Invoked when the "Select" button is pushed
    public String connect() {
        if (finder.searchOrganizerByEmail(getEmail()).isPresent()) {
            return Signal.CONNECTED;
        } else {
            FacesContext.getCurrentInstance()
                    .addMessage("form-error", new FacesMessage("Unknown organizer: " + getEmail()));
            return Signal.UNKNOWN;
        }
    }

    // Invoked when the "Register" button is pushed
    public String register() {
        try {
            registry.registerOrganizer(getName(), getEmail(), getPassword(), getPhone());
            return Signal.ADDED;
        } catch (AlreadyExistingOrganizerException e) {
            log.log(Level.WARNING, "Unknown customer", e);
            FacesContext.getCurrentInstance()
                    .addMessage("form-error", new FacesMessage("Organizer " + getEmail() + " already exists!"));
            return Signal.EXISTING;
        }
    }

}
