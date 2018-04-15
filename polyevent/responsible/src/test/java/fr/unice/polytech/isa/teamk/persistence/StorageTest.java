package fr.unice.polytech.isa.teamk.persistence;

import arquillian.AbstractPolyeventTest;
import fr.unice.polytech.isa.teamk.entities.user.Responsible;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.COMMIT)
public class StorageTest extends AbstractPolyeventTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void storingResponsible() throws Exception {
        Responsible c = new Responsible("John Doe", "1234567890", "lolilol@gmail.com", "0474015640");
        assertEquals("0",c.getId());

        entityManager.persist(c);
        String id = c.getId();
        assertNotEquals("0",id);

        Responsible stored = (Responsible) entityManager.find(Responsible.class, id);
        assertEquals(c, stored);
    }

}
