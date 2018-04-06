package arquillian;

import fr.unice.polytech.isa.teamk.Payment;
import fr.unice.polytech.isa.teamk.components.PaymentBean;
import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.entities.user.Organizer;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public abstract class AbstractAccountingTest {

    @Deployment
    public static WebArchive createDeployment() {
        // Building a Web ARchive (WAR) containing the following elements:
        return ShrinkWrap.create(WebArchive.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                // Entities
                .addPackage(Event.class.getPackage())
                .addPackage(Organizer.class.getPackage())
                // Components Interfaces
                .addPackage(Payment.class.getPackage())
                // Components implementations
                .addPackage(PaymentBean.class.getPackage());
    }

}
