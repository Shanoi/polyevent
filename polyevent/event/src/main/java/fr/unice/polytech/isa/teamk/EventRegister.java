package fr.unice.polytech.isa.teamk;

import fr.unice.polytech.isa.teamk.exceptions.AlreadyExistingEventException;
import fr.unice.polytech.isa.teamk.exceptions.RegisterEventException;
import fr.unice.polytech.isa.teamk.exceptions.UnknownEventException;

import javax.ejb.Local;
import java.util.List;

@Local
public interface EventRegister {

    String submitNewEvent(String eventName, String startDate, String endDate, int nbAttendee, String organizerEmail) throws AlreadyExistingEventException;

    boolean confirmEvent(String eventName, List<String> rooms) throws RegisterEventException, UnknownEventException;

}
