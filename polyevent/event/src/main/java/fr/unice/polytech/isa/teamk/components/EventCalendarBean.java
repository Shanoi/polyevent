package fr.unice.polytech.isa.teamk.components;

import fr.unice.polytech.isa.teamk.EventFinder;
import fr.unice.polytech.isa.teamk.ResponsibleFinder;
import fr.unice.polytech.isa.teamk.entities.Event;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Optional;

@Stateless
public class EventCalendarBean implements EventFinder {

    @EJB
    private ResponsibleFinder responsibleFinder;

    @EJB
    private DatabaseSingletonBean databaseSingletonBean;

    public EventCalendarBean() {

    }

    @Override
    public Optional<Event> searchEvenement(String idEvent) {
        return databaseSingletonBean.findEventByID(idEvent);
    }

    @Override
    public List<Event> getAllWaitingEvents() {
        return databaseSingletonBean.getWaitingList();
    }

}
