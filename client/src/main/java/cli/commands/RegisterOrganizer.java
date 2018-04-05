package cli.commands;


import api.PEPublicAPI;
import cli.framework.Command;

import java.util.List;

public class RegisterOrganizer extends Command<PEPublicAPI> {

    private String name;
    private String id;

    @Override
    public String identifier() { return "register"; }

    @Override
    public void load(List<String> args) {
        name= args.get(0);
        id = args.get(1);
    }

    @Override
    public void execute() throws Exception {
        shell.system.organizerService.registerOrganizer(name,id);
    }

    @Override
    public String describe() {
        return "Register a new Organizer in the PolyEvent system\n" +
                "	--> register <Organizer Name> <Organizer ID>";
    }
}
