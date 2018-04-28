package fr.unice.polytech.isa.teamk.components;

import fr.unice.polytech.isa.teamk.ProviderFinder;
import fr.unice.polytech.isa.teamk.entities.Provider;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class ProviderRegistryBean implements ProviderFinder {

    private static final Logger log = Logger.getLogger(Logger.class.getName());

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Provider> searchProviders() {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Provider> criteria = builder.createQuery(Provider.class);
        Root<Provider> root = criteria.from(Provider.class);
        criteria.select(root);
        TypedQuery<Provider> query = manager.createQuery(criteria);

        try {
            return query.getResultList();
        } catch (NoResultException nre) {
            log.log(Level.FINEST, "No result for all providers", nre);
            return new ArrayList<>();
        }
    }
}
