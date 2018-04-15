package fr.unice.polytech.isa.teamk;

import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.exceptions.RegisterEventException;

import javax.ejb.Local;

@Local
public interface EventRegister {

    void submitNewEvent(Event event, String organizerEmail);

    boolean confirmEvent(Event event) throws RegisterEventException;

}
