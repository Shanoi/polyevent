package fr.unice.polytech.isa.teamk.components.integration;

import arquillian.AbstractOrganizerTest;
import fr.unice.polytech.isa.teamk.OrganizerFinder;
import fr.unice.polytech.isa.teamk.OrganizerRegister;
import fr.unice.polytech.isa.teamk.entities.user.Organizer;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import static org.junit.Assert.assertFalse;

@RunWith(Arquillian.class)
public class OrganizerRegistrationIntegrationTest extends AbstractOrganizerTest {

    @EJB
    private OrganizerRegister registry;

    @EJB
    private OrganizerFinder finder;

    private Organizer organizer;

    @Before
    public void setUpContext() {
        organizer = new Organizer("Poggers", "pitmfc");
    }

    @Test
    public void dummyTest() {
        assertFalse(false);
    }

    @Ignore
    public void integrationForOrganizerRegistering() throws Exception {
       /* registry.registerOrganizer(organizer.getId(), organizer.getPassword());
        Optional<Organizer> customer = finder.searchOrganisateurByID(organizer.getId());
        assertTrue(customer.isPresent());
        Organizer retrieved = customer.get();
        assertEquals(organizer.getId(), retrieved.getId());
        assertEquals(organizer.getPassword(), retrieved.getPassword());*/
    }

}
