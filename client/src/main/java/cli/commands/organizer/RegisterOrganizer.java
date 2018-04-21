package cli.commands.organizer;

import api.PEPublicAPI;
import cli.framework.Command;
import stubs.organizerstubs.AlreadyExistingOrganizerException_Exception;

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
        short argIndex = 0;

        name = args.get(argIndex++);
        email = args.get(argIndex++);
        password = args.get(argIndex++);
        phone = args.get(argIndex);
    }

    @Override
    public void execute() {
        try {
            shell.system.organizerService.registerOrganizer(name, email, password, phone);
        } catch (AlreadyExistingOrganizerException_Exception e) {
            System.err.println("The organizer with email '" + e.getFaultInfo().getEmail() + "' already exists.");
        }
    }

    @Override
    public String describe() {
        return "Register a new Organizer in the PolyEvent system\n" +
                "	--> register_organizer <Organizer Name> <Organizer Email> <Organizer Password> <Organizer Phone>";
    }

}
