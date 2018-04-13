package fr.unice.polytech.isa.teamk.components;

import fr.unice.polytech.isa.teamk.EventFinder;
import fr.unice.polytech.isa.teamk.EventRegister;
import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.exceptions.RegisterEventException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class EventRegistryBean implements EventRegister, EventFinder {

    private static final Logger log = Logger.getLogger(Logger.class.getName());

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean registerEvent(Event event, String roomID) throws RegisterEventException {

        if(searchEvenement(event.getID()).isPresent())
            throw new RegisterEventException(event.getName(), "bullshit");

       /* Event c = new Customer();
        c.setName(name);
        c.setCreditCard(creditCard);
        c.setCart(new HashSet<>());

        manager.persist(c);*/

        return false;
    }

    @Override
    public Optional<Event> searchEvenement(String idEvent) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Event> criteria = builder.createQuery(Event.class);
        Root<Event> root = criteria.from(Event.class);
        criteria.select(root).where(builder.equal(root.get("id"), idEvent));
        TypedQuery<Event> query = manager.createQuery(criteria);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException nre) {
            log.log(Level.FINEST, "No result for [" + idEvent + "]", nre);
            return Optional.empty();
        }

    }

    @Override
    public List<Event> getAllWaitingEvents() {
        return null;
    }
}
