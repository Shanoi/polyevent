package fr.unice.polytech.isa.teamk.components;

import fr.unice.polytech.isa.teamk.EventTracker;
import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.entities.Provider;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class EventTrackerBean implements EventTracker {

    @EJB
    private DatabaseSingletonBean databaseSingletonBean;

    public EventTrackerBean() {

    }

    @Override
    public void forwardNewEvent(Event event) {
        this.databaseSingletonBean.addToWaitingList(event);
    }

    @Override
    public boolean forwardNewAttendeeNb(Event event) {
        return false;
    }

    @Override
    public boolean forwardNewStartDate(Event event) {
        return false;
    }

    @Override
    public boolean forwardNewEndDate(Event event) {
        return false;
    }

    @Override
    public boolean forwardNewMaterial(Event event, String materialType) {
        return false;
    }

    @Override
    public boolean forwardNewService(Event event, Provider provider) {
        return false;
    }

    @Override
    public boolean forwardDelService(Event event, String providerType) {
        return false;
    }

    @Override
    public boolean forwardDelMaterial(Event event, String materialType) {
        return false;
    }

}