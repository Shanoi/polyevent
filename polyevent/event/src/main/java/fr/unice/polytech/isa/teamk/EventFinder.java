package fr.unice.polytech.isa.teamk;

import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.entities.EventStatus;
import fr.unice.polytech.isa.teamk.entities.Organizer;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Local
public interface EventFinder {

    Optional<Event> searchEventByName(String name);

    List<Event> searchEventByStatus(EventStatus status);

    Set<Event> searchEventByOrganizer(String email);

}
