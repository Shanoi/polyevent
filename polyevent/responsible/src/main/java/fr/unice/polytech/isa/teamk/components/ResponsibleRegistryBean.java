package fr.unice.polytech.isa.teamk.components;

import fr.unice.polytech.isa.teamk.ResponsibleFinder;
import fr.unice.polytech.isa.teamk.ResponsibleRegister;
import fr.unice.polytech.isa.teamk.entities.Organizer;
import fr.unice.polytech.isa.teamk.entities.Responsible;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyExistingResponsibleException;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyLoggedInResponsibleException;
import fr.unice.polytech.isa.teamk.exceptions.UnknownResponsibleException;

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
public class ResponsibleRegistryBean implements ResponsibleRegister, ResponsibleFinder {

    private static final Logger log = Logger.getLogger(Logger.class.getName());

    @PersistenceContext
    private EntityManager manager;

    /**
     * Public constructor for ejb purpose.
     */
    public ResponsibleRegistryBean() {

    }

    @Override
    public void registerResponsible(String name, String email, String password, String phone) throws AlreadyExistingResponsibleException {
        if (searchResponsibleByEmail(email).isPresent()) {
            throw new AlreadyExistingResponsibleException(email);
        }

        Responsible responsible = new Responsible(name, email, password, phone);

        manager.persist(responsible);
    }

    @Override
    public void loginResponsible(String email, String password) throws UnknownResponsibleException, AlreadyLoggedInResponsibleException {
        Optional<Responsible> responsible = searchResponsibleByEmail(email);

        if (responsible.isPresent() && responsible.get().getPassword().equals(password)) {
            if (!responsible.get().getLoggedIn()) {
                responsible.get().setLoggedIn(true);
                return;
            } else {
                throw new AlreadyLoggedInResponsibleException(email);
            }
        }

        throw new UnknownResponsibleException(email);
    }

    @Override
    public void disconnectResponsible(String email) {
        Optional<Responsible> responsible = searchResponsibleByEmail(email); // Safe check, but useless since you cannot
        // log off if you never logged in.

        responsible.ifPresent(organizer1 -> organizer1.setLoggedIn(false));
    }

    @Override
    public Optional<Responsible> searchResponsibleByEmail(String email) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Responsible> criteria = builder.createQuery(Responsible.class);
        Root<Responsible> root = criteria.from(Responsible.class);
        criteria.select(root).where(builder.equal(root.get("email"), email));
        TypedQuery<Responsible> query = manager.createQuery(criteria);

        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException nre) {
            log.log(Level.FINEST, "No result for [" + email + "]", nre);
            return Optional.empty();
        }
    }

}
