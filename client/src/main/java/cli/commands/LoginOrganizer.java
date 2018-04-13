package cli.commands;

import api.PEPublicAPI;
import cli.framework.Command;
import stubs.eventstubs.Organizer;

import java.util.List;

public class LoginOrganizer extends Command<PEPublicAPI> {

    private String id;
    private String password;
    private String loggedInOrganizerId;

    @Override
    public String identifier() {
        return "login_organizer";
    }

    @Override
    public void load(List<String> args) {
        id = args.get(0);
        password = args.get(1);
    }

    @Override
    public void execute() {
        try {
            shell.system.organizerService.loginOrganizer(id, password);
            loggedInOrganizerId = id;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String describe() {
        return "LoginOrganizer an existing Organizer in the PolyEvent system\n" +
                "	--> login_organizer <Organizer ID> <Organizer Password>";
    }

}
