package fr.unice.polytech.isa.teamk;

import fr.unice.polytech.isa.teamk.entities.Responsible;

import java.util.Optional;

public interface ResponsibleFinder {

    Optional<Responsible> searchResponsibleByID(String id);

    Optional<Responsible> searchResponsible(String name);

}
