package fr.unice.polytech.isa.teamk;

import fr.unice.polytech.isa.teamk.entities.Responsible;

import javax.ejb.Local;
import java.util.Optional;

@Local
public interface ResponsibleFinder {

    Optional<Responsible> searchResponsibleByEmail(String email);

}
