package fr.unice.polytech.isa.teamk;

import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.entities.EventStatus;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface EventFinder {

    Optional<Event> searchEventByName(String name);

    List<Event> searchEventByStatus(EventStatus status);

}
