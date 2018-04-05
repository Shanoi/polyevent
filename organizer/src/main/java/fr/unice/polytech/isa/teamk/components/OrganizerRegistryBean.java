package fr.unice.polytech.isa.teamk.components;

import fr.unice.polytech.isa.teamk.OrganizerFinder;
import fr.unice.polytech.isa.teamk.OrganizerRegister;
import fr.unice.polytech.isa.teamk.entities.users.Organizer;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyExistingOrganizer;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Optional;

@Stateless
public class OrganizerRegistryBean implements OrganizerRegister, OrganizerFinder {

    @EJB
    private DatabaseSingletonBean databaseSingletonBean;

    public OrganizerRegistryBean(){

    }

    @Override
    public Optional<Organizer> searchOrganisateurByID(String idOrganizer) {
        return databaseSingletonBean.findOrganizerByID(idOrganizer);
    }

    @Override
    public Optional<Organizer> searchOrganisateurByName(String name) {
        return Optional.empty();
    }

    @Override
    public boolean registerOrganizer(String name, String id) throws AlreadyExistingOrganizer {
        if(searchOrganisateurByID(id).isPresent()){
            throw new AlreadyExistingOrganizer(id);
        }

        databaseSingletonBean.createNewOrganizer(new Organizer(name, id));

        return true;
    }

}