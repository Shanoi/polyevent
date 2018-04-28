package fr.unice.polytech.isa.teamk.managed;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@ManagedBean
@SessionScoped
public class EventBean implements Serializable {

    private static final Logger log = Logger.getLogger(EventBean.class.getName());

    private String name;
    private String startDate;
    private String endDate;
    private int nbAttendee;
    private List<String> partners;
    private List selPartners;

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
}
