package cli.commands;

import api.PEPublicAPI;
import cli.framework.Command;

import java.util.List;

public class LoginOrganizer extends Command<PEPublicAPI> {

    private String id;
    private String password;

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
    public void execute() throws Exception {
        shell.system.organizerService.loginOrganizer(id, password);
    }

    @Override
    public String describe() {
        return "LoginOrganizer an existing Organizer in the PolyEvent system\n" +
                "	--> login_organizer <Organizer ID> <Organizer Password>";
    }

}
