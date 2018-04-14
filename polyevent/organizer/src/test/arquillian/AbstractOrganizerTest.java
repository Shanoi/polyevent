package arquillian;

import fr.unice.polytech.isa.teamk.OrganizerFinder;
import fr.unice.polytech.isa.teamk.OrganizerRegister;
import fr.unice.polytech.isa.teamk.components.OrganizerRegistryBean;
import fr.unice.polytech.isa.teamk.entities.user.Organizer;
import fr.unice.polytech.isa.teamk.utils.DatabaseSingletonOrganizer;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import javax.ejb.EJB;

public abstract class AbstractOrganizerTest {

    @EJB
    private DatabaseSingletonOrganizer memory;

    @Deployment
    public static WebArchive createDeployment() {
        // Building a Web ARchive (WAR) containing the following elements:
        return ShrinkWrap.create(WebArchive.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                // Entities
                .addPackage(Organizer.class.getPackage())
                // Components Interfaces
                .addPackage(OrganizerRegister.class.getPackage())
                .addPackage(OrganizerFinder.class.getPackage())
                // Components implementations
                .addPackage(OrganizerRegistryBean.class.getPackage())
                .addPackage(DatabaseSingletonOrganizer.class.getPackage());
    }

}
