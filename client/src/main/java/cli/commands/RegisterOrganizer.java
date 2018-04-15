package cli.commands;

import api.PEPublicAPI;
import cli.framework.Command;

import java.util.List;

public class RegisterOrganizer extends Command<PEPublicAPI> {

    private String name;
    private String email;
    private String password;
    private String phone;

    @Override
    public String identifier() {
        return "register_organizer";
    }

    @Override
    public void load(List<String> args) {
        name = args.get(0);
        email = args.get(1);
        password = args.get(2);
        phone = args.get(3);
    }

    @Override
    public void execute() throws Exception {
        shell.system.organizerService.registerOrganizer(name, email, password, phone);
    }

    @Override
    public String describe() {
        return "Register a new Organizer in the PolyEvent system\n" +
                "	--> register_organizer <Organizer Name> <Organizer Email> <Organizer Password> <Organizer Phone>";
    }

}
