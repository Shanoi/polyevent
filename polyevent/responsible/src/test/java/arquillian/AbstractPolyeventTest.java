package arquillian;

import fr.unice.polytech.isa.teamk.ResponsibleFinder;
import fr.unice.polytech.isa.teamk.ResponsibleRegister;
import fr.unice.polytech.isa.teamk.entities.user.Responsible;
import fr.unice.polytech.isa.teamk.exceptions.RegisterEventException;
import fr.unice.polytech.isa.teamk.exceptions.UncheckedException;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import javax.ejb.EJB;

public abstract class AbstractPolyeventTest {

    @Deployment
    public static WebArchive createDeployment() {
        // Building a Web ARchive (WAR) containing the following elements:
        return ShrinkWrap.create(WebArchive.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addPackage(Responsible.class.getPackage())
                .addPackage(RegisterEventException.class.getPackage())
                .addPackage(UncheckedException.class.getPackage())
                .addPackage(ResponsibleFinder.class.getPackage())
                .addPackage(ResponsibleRegister.class.getPackage())
                // Persistence file
                .addAsManifestResource(new ClassLoaderAsset("META-INF/persistence.xml"), "persistence.xml");
    }


}
