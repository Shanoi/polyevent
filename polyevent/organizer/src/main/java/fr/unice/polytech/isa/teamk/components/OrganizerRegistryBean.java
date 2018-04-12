package fr.unice.polytech.isa.teamk.components;

import fr.unice.polytech.isa.teamk.OrganizerFinder;
import fr.unice.polytech.isa.teamk.OrganizerRegister;
import fr.unice.polytech.isa.teamk.entities.user.Organizer;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyExistingOrganizer;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyLoggedInOrganizer;
import fr.unice.polytech.isa.teamk.exceptions.UnknownOrganizerException;
import fr.unice.polytech.isa.teamk.utils.DatabaseSingletonOrganizer;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Optional;

@Stateless
public class OrganizerRegistryBean implements OrganizerRegister, OrganizerFinder {

    @EJB
    private DatabaseSingletonOrganizer databaseSingleton;

    /**
     * Public constructor for ejb purpose.
     */
    public OrganizerRegistryBean() {

    }

    @Override
    public boolean registerOrganizer(String id, String password) throws AlreadyExistingOrganizer {
        if (!databaseSingleton.createNewOrganizer(new Organizer(id, password))) {
            throw new AlreadyExistingOrganizer(id);
        }

        return true;
    }

    @Override
    public boolean loginOrganizer(String id, String password) throws UnknownOrganizerException, AlreadyLoggedInOrganizer {
        Optional<Organizer> organizer = searchOrganizerByID(id);
        if (organizer.isPresent() && organizer.get().getPassword().equals(password)) {
            if (databaseSingleton.loginOrganizer(organizer.get())) {
                return true;
            } else {
                throw new AlreadyLoggedInOrganizer(id);
            }
        }

        throw new UnknownOrganizerException(id);
    }

    @Override
    public Optional<Organizer> searchOrganizerByID(String id) {
        return databaseSingleton.findOrganizerByID(id);
    }

}
