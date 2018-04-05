package fr.unice.polytech.isa.teamk;

import fr.unice.polytech.isa.teamk.entities.users.Organizer;

import javax.ejb.Local;
import java.util.Optional;

@Local
public interface OrganizerFinder {

    Optional<Organizer> searchOrganizerByID(String idOrganizer);

}