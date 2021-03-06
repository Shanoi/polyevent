import api.PEPublicAPI;
import cli.commands.Bye;
import cli.commands.organizer.GetEventsStatus;
import cli.commands.organizer.LoginOrganizer;
import cli.commands.organizer.RegisterOrganizer;
import cli.commands.organizer.SubmitEvent;
import cli.commands.responsible.*;
import cli.framework.Shell;

/**
 * An Interactive shell that interacts with a Cookie on Demand instance
 * Use -Dexec.args="IP_ADDRESS PORT_NUMBER" to change host/port parameters
 */
public class Main extends Shell<PEPublicAPI> {

    public Main(String host, String port) {

        this.system = new PEPublicAPI(host, port);
        this.invite = "PolyEvent";

        // Registering the command available for the user
        register(
                // Getting out of here
                Bye.class,
                // Handling Organizer event
                RegisterOrganizer.class,
                LoginOrganizer.class,
                SubmitEvent.class,
                GetEventsStatus.class,
                // Handling Responsible event
                RegisterResponsible.class,
                LoginResponsible.class,
                GetSubmittedEvents.class,
                GetVacantRooms.class,
                ConfirmEvent.class
        );
    }

    public static void main(String[] args) {
        String host = (args.length == 0 ? "localhost" : args[0]);
        String port = (args.length < 2 ? "8080" : args[1]);
        System.out.println("\n\nStarting PolyEvent");
        System.out.println("  - Remote server: " + host);
        System.out.println("  - Port number:   " + port);
        Main main = new Main(host, port);
        main.run();
        System.out.println("Exiting PolyEvent\n\n");
    }

}
