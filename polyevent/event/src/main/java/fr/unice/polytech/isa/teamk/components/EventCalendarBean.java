package fr.unice.polytech.isa.teamk.components;

import fr.unice.polytech.isa.teamk.EventFinder;
import fr.unice.polytech.isa.teamk.ResponsibleFinder;
import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.utils.DatabaseSingletonEvent;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Optional;

@Stateless
public class EventCalendarBean implements EventFinder {

    @EJB
    private ResponsibleFinder responsibleFinder;

    @EJB
    private DatabaseSingletonEvent databaseSingletonEvent;

    public EventCalendarBean() {

    }

    @Override
    public Optional<Event> searchEvenement(String idEvent) {
        return databaseSingletonEvent.findEventByID(idEvent);
    }

    @Override
    public List<Event> getAllWaitingEvents() {
        return databaseSingletonEvent.getEvents();
    }

}
