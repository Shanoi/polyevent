package cli.commands.organizer;

import api.PEPublicAPI;
import cli.framework.Command;
import stubs.organizerstubs.AlreadyLoggedInOrganizerException_Exception;
import stubs.organizerstubs.UnknownOrganizerException_Exception;

import java.util.List;

public class LoginOrganizer extends Command<PEPublicAPI> {

    private String email;
    private String password;
    public static String loggedInOrganizerId = "";

    @Override
    public String identifier() {
        return "login_organizer";
    }

    @Override
    public void load(List<String> args) {
        short argIndex = 0;

        email = args.get(argIndex++);
        password = args.get(argIndex);
    }

    @Override
    public void execute() {
        try {
            shell.system.organizerService.loginOrganizer(email, password);
            loggedInOrganizerId = email;
        } catch (AlreadyLoggedInOrganizerException_Exception e) {
            if (loggedInOrganizerId.isEmpty()) {
                loggedInOrganizerId = email;
            } else {
                System.err.println("The organizer with email '" + e.getFaultInfo().getEmail() + "' is already logged in.");
            }
        } catch (UnknownOrganizerException_Exception e) {
            System.err.println("The organizer with email '" + e.getFaultInfo().getEmail() + "' is unknown.");
        }
    }

    @Override
    public String describe() {
        return "Login an existing Organizer in the PolyEvent system\n" +
                "	--> login_organizer <Organizer Email> <Organizer Password>";
    }

}
