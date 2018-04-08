package fr.unice.polytech.isa.teamk.components;

import fr.unice.polytech.isa.teamk.OrganizerFinder;
import fr.unice.polytech.isa.teamk.OrganizerRegister;
import fr.unice.polytech.isa.teamk.entities.user.Organizer;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyExistingOrganizer;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Optional;

@Stateless
public class OrganizerRegistryBean implements OrganizerRegister, OrganizerFinder {

    @EJB
    private DatabaseSingletonOrganizer databaseSingletonBean;

    public OrganizerRegistryBean(){

    }

    @Override
    public boolean registerOrganizer(String id, String password) throws AlreadyExistingOrganizer {
        if(databaseSingletonBean.createNewOrganizer(new Organizer(id, password))) {
            throw new AlreadyExistingOrganizer(id);
        }

        return true;
    }

    @Override
    public Optional<Organizer> searchOrganizerByID(String id) {
        return databaseSingletonBean.findOrganizerByID(id);
    }

}