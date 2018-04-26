package fr.unice.polytech.isa.teamk;

import fr.unice.polytech.isa.teamk.entities.EventStatus;
import fr.unice.polytech.isa.teamk.exceptions.UnknownEventException;

import javax.ejb.Local;

@Local
public interface Tracker {

    EventStatus status(String eventId) throws UnknownEventException;

}
