package cli.commands.responsible;

import api.PEPublicAPI;
import cli.framework.Command;

import java.util.List;

public class RegisterResponsible extends Command<PEPublicAPI> {

    private String name;
    private String email;
    private String password;
    private String phone;

    @Override
    public String identifier() {
        return "register_responsible";
    }

    @Override
    public void load(List<String> args) {
        name = args.get(0);
        email = args.get(1);
        password = args.get(2);
        phone = args.get(3);
    }

    @Override
    public void execute() {
        /*try {
            shell.system.responsibleService.registerResponsible(name, email, password, phone);
        } catch (AlreadyExistingResponsible_Exception e) {
            System.err.println("The responsible with email '" + e.getFaultInfo().getEmail() + "' already exists.");
        }*/
    }

    @Override
    public String describe() {
        return "Register a new Responsible in the PolyEvent system\n" +
                "	--> register_responsible <Responsible Name> <Responsible Email> <Responsible Password> <Responsible Phone>";
    }

}
