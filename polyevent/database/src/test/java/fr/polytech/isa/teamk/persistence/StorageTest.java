package fr.polytech.isa.teamk.persistence;

import arquillian.AbstractPersistenceTest;
import fr.unice.polytech.isa.teamk.entities.*;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.COMMIT)
public class StorageTest extends AbstractPersistenceTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void storingEvent() throws Exception {
        //String str = "14-03-2018 12:30";
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        //LocalDateTime.parse(str, formatter)

        Event c = new Event("PolyDating", Timestamp.valueOf("2015-03-30 14:37:00"), Timestamp.valueOf("2015-03-30 14:37:00"), 150);
        assertEquals(0, c.getId());

        entityManager.persist(c);
        int id = c.getId();
        assertNotEquals(0, id);

        Event stored = (Event) entityManager.find(Event.class, id);
        assertEquals(c, stored);
    }

    @Test
    public void storingMaterial() throws Exception {
        Material c = new Material(1, "Sonic Screwdriver");
        assertEquals(0, c.getId());

        entityManager.persist(c);
        int id = c.getId();
        assertNotEquals(0, id);

        Material stored = (Material) entityManager.find(Material.class, id);
        assertEquals(c, stored);
    }

    @Test
    public void storingOrganizer() throws Exception {
        Organizer c = new Organizer("23 à 0", "C'est la piquette Jack", "Tu sais pas jouer Jack", "T'es mauvais!");
        assertEquals(0, c.getId());

        entityManager.persist(c);
        int id = c.getId();
        assertNotEquals(0, id);

        Organizer stored = (Organizer) entityManager.find(Organizer.class, id);
        assertEquals(c, stored);
    }

    @Test
    public void storingProvider() throws Exception {
        Provider c = new Provider("Otis mon Scribe", "Près de la pyramide", "Le numéros bis", "otis.scribe@tablettedargile.com", "Scribe", 544);
        assertEquals(0, c.getId());

        entityManager.persist(c);
        int id = c.getId();
        assertNotEquals(0, id);

        Provider stored = (Provider) entityManager.find(Provider.class, id);
        assertEquals(c, stored);
    }

    @Test
    public void storingQuote() throws Exception {
        Event event = new Event("Bataille du Gouffre de Helm", Timestamp.valueOf("2015-03-30 14:37:00"), Timestamp.valueOf("2015-03-30 14:37:00"), 150);
        entityManager.persist(event);

        Quote c = new Quote(544.12f, "Décarre tes troupes de chez moi ou j'crame ton pays. C'est assez simple comme vocabulaire ?", event);
        assertEquals(0, c.getId());

        entityManager.persist(c);
        int id = c.getId();
        assertNotEquals(0, id);

        Quote stored = (Quote) entityManager.find(Quote.class, id);
        Assert.assertEquals(event, stored.getEvent());
        Assert.assertEquals(c, stored); // the all together
    }

    @Test
    public void storingResponsible() throws Exception {
        Responsible c = new Responsible("John Doe", "1234567890", "lolilol@gmail.com", "0474015640");
        Assert.assertEquals(0, c.getId());

        entityManager.persist(c);
        int id = c.getId();
        assertNotEquals(0, id);

        Responsible stored = (Responsible) entityManager.find(Responsible.class, id);
        Assert.assertEquals(c, stored);
    }

    @Test
    public void storingRoom() throws Exception {
        Room c = new Room(150, "Middle-earth");
        assertEquals(0, c.getId());

        entityManager.persist(c);
        int id = c.getId();
        assertNotEquals(0, id);

        Room stored = (Room) entityManager.find(Room.class, id);
        assertEquals(c, stored);
    }

}
