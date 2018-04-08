package fr.unice.polytech.isa.teamk.components;

import fr.unice.polytech.isa.teamk.entities.user.Organizer;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Startup
@Singleton
public class DatabaseSingletonOrganizer {

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


}
