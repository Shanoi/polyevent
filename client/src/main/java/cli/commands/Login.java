package cli.commands;

import api.PEPublicAPI;
import cli.framework.Command;

import java.util.List;

public class Login extends Command<PEPublicAPI> {

    private String id;
    private String password;

    @Override
    public String identifier() { return "login"; }

    @Override
    public void load(List<String> args) {
        id = args.get(0);
        password = args.get(1);
    }

    @Override
    public void execute() throws Exception {
        shell.system.organizerService.registerOrganizer(id, password);
    }

    @Override
    public String describe() {
        return "Register a new Organizer in the PolyEvent system\n" +
                "	--> register <Organizer ID> <Organizer Password>";
    }

}
