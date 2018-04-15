package fr.unice.polytech.isa.teamk;

import fr.unice.polytech.isa.teamk.entities.Organizer;

import javax.ejb.Local;
import java.util.Optional;

@Local
public interface OrganizerFinder {

    Optional<Organizer> searchOrganizerByEmail(String email);

}
