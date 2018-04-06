package fr.unice.polytech.isa.teamk;


import fr.unice.polytech.isa.teamk.entities.Event;

import java.util.List;
import java.util.Optional;

public interface EventFinder {

    Optional<Event> searchEvenement(String idEvent);

    List<Event> getAllWaitingEvents();

}