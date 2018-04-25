package fr.unice.polytech.isa.teamk.components;

import fr.unice.polytech.isa.teamk.OrganizerFinder;
import fr.unice.polytech.isa.teamk.OrganizerRegister;
import fr.unice.polytech.isa.teamk.entities.Organizer;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyExistingOrganizerException;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyLoggedInOrganizerException;
import fr.unice.polytech.isa.teamk.exceptions.UnknownOrganizerException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class OrganizerRegistryBean implements OrganizerRegister, OrganizerFinder {

    private static final Logger log = Logger.getLogger(Logger.class.getName());

    @PersistenceContext
    private EntityManager manager;

    /**
     * Public constructor for ejb purpose.
     */
    public OrganizerRegistryBean() {

    }

    @Override
    public void registerOrganizer(String name, String email, String password, String phone) throws AlreadyExistingOrganizerException {
        if (searchOrganizerByEmail(email).isPresent()) {
            throw new AlreadyExistingOrganizerException(email);
        }

        Organizer organizer = new Organizer(name, email, password, phone);

        manager.persist(organizer);
    }

    @Override
    public void loginOrganizer(String email, String password) throws UnknownOrganizerException, AlreadyLoggedInOrganizerException {
        Optional<Organizer> organizer = searchOrganizerByEmail(email);

        if (organizer.isPresent() && organizer.get().getPassword().equals(password)) {
            if (!organizer.get().getLoggedIn()) {
                organizer.get().setLoggedIn(true);
                return;
            } else {
                throw new AlreadyLoggedInOrganizerException(email);
            }
        }

        throw new UnknownOrganizerException(email);
    }

    @Override
    public void disconnectOrganizer(String email) {
        Optional<Organizer> organizer = searchOrganizerByEmail(email); // Safe check, but useless since you cannot log
        // off if you never logged in.

        organizer.ifPresent(organizer1 -> organizer1.setLoggedIn(false));
    }

    @Override
    public Optional<Organizer> searchOrganizerByEmail(String email) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Organizer> criteria = builder.createQuery(Organizer.class);
        Root<Organizer> root = criteria.from(Organizer.class);
        criteria.select(root).where(builder.equal(root.get("email"), email));
        TypedQuery<Organizer> query = manager.createQuery(criteria);

        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException nre) {
            log.log(Level.FINEST, "No result for [" + email + "]", nre);
            return Optional.empty();
        }
    }

}
