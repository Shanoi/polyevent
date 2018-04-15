package cli.commands;

import api.PEPublicAPI;
import cli.framework.Command;

import java.util.List;

public class LoginOrganizer extends Command<PEPublicAPI> {

    private String email;
    private String password;
    public static String loggedInOrganizerId;

    @Override
    public String identifier() {
        return "login_organizer";
    }

    @Override
    public void load(List<String> args) {
        email = args.get(0);
        password = args.get(1);
    }

    @Override
    public void execute() {
        try {
            shell.system.organizerService.loginOrganizer(email, password);
            loggedInOrganizerId = email;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String describe() {
        return "LoginOrganizer an existing Organizer in the PolyEvent system\n" +
                "	--> login_organizer <Organizer Email> <Organizer Password>";
    }

}
