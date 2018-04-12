package fr.unice.polytech.isa.teamk.components;

import fr.unice.polytech.isa.teamk.OrganizerFinder;
import fr.unice.polytech.isa.teamk.OrganizerRegister;
import fr.unice.polytech.isa.teamk.entities.user.Organizer;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyExistingOrganizer;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Startup
@Singleton
public class DatabaseSingletonOrganizer implements OrganizerFinder, OrganizerRegister {

    private Set<Organizer> organizerList;

    public DatabaseSingletonOrganizer() {

    }

    @PostConstruct
    void init() {
        this.organizerList = new HashSet<>();

    }

    public Optional<Organizer> findOrganizerByID(String id) {
        for (Organizer organizer : organizerList) {
            if (organizer.getId().equals(id)) {
                return Optional.of(organizer);
            }
        }

        return Optional.empty();
    }

    public boolean createNewOrganizer(Organizer organizer) {
        return organizerList.add(organizer);
    }

    @Override
    public Optional<Organizer> searchOrganizerByID(String idOrganizer) {
        for (Organizer organizer : organizerList) {
            if (organizer.getId().equals(idOrganizer)) {
                return Optional.of(organizer);
            }
        }

        return Optional.empty();
    }

    @Override
    public boolean registerOrganizer(String id, String password) throws AlreadyExistingOrganizer {
        return organizerList.add(new Organizer(id, password));
    }
}
