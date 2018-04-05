package fr.unice.polytech.isa.teamk.components.integration;

import arquillian.AbstractAccountingTest;
import fr.unice.polytech.isa.teamk.Payment;
import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.entities.users.Organizer;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertFalse;

@RunWith(Arquillian.class)
public class PaymentIntegrationTest extends AbstractAccountingTest {

    @EJB
    private Payment cashier;

    private Organizer organizer;
    private Event event;

    @Before
    public void setUpContext() {
        organizer = new Organizer("Jenkins", "jit");
        event = new Event("Test", new GregorianCalendar(), new GregorianCalendar(), organizer);
    }

    @Test
    public void integrationForPayment() throws Exception {
        assertFalse(cashier.pay(15, event));
    }

}
