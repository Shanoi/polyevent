package arquillian;

import fr.unice.polytech.isa.teamk.ResponsibleFinder;
import fr.unice.polytech.isa.teamk.ResponsibleRegister;
import fr.unice.polytech.isa.teamk.entities.Responsible;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public abstract class AbstractResponsibleTest {

    @Deployment
    public static WebArchive createDeployment() {
        // Building a Web ARchive (WAR) containing the following elements:
        return ShrinkWrap.create(WebArchive.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addPackage(Responsible.class.getPackage())
                .addPackage(ResponsibleFinder.class.getPackage())
                .addPackage(ResponsibleRegister.class.getPackage());
    }

}
