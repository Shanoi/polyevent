package fr.unice.polytech.isa.teamk;

import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyExistingEvent;
import fr.unice.polytech.isa.teamk.exceptions.RegisterEventException;

import javax.ejb.Local;

@Local
public interface EventRegister {

    void submitNewEvent(String eventName, String startDate, String endDate, int nbAttendee, String organizerEmail) throws AlreadyExistingEvent;

    boolean confirmEvent(Event event) throws RegisterEventException;

}
