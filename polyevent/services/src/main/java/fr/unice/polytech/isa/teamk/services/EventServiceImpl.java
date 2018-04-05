package fr.unice.polytech.isa.teamk.services;

import fr.unice.polytech.isa.teamk.EventTracker;
import fr.unice.polytech.isa.teamk.OrganizerFinder;
import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.entities.users.Organizer;
import fr.unice.polytech.isa.teamk.exceptions.UnknownOrganizerException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.GregorianCalendar;
import java.util.Optional;

@Stateless(name = "EventWS")
@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/event")
public class EventServiceImpl implements EventService {

    @EJB
    private EventTracker eventTracker;

    @EJB
    private OrganizerFinder organizerFinder;

    public EventServiceImpl() {

    }

    @Override
    public void submitNewEvent(String name, String idOrganizer, short startDay, short startMonth, short startYear, short endDay, short endMonth, short endYear)
        throws UnknownOrganizerException {
        Optional<Organizer> organizer = organizerFinder.searchOrganisateurByID(idOrganizer);

        if(organizer.isPresent()){
            eventTracker.forwardNewEvent(
                    new Event(name,
                            new GregorianCalendar(startYear, startMonth-1, startDay, 0, 0, 0),
                            new GregorianCalendar(endYear, endMonth-1, endDay, 0, 0, 0),
                            organizer.get()));
        } else {
            throw new UnknownOrganizerException(idOrganizer);
        }
    }

}