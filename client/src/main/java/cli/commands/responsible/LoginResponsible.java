package cli.commands.responsible;

import api.PEPublicAPI;
import cli.framework.Command;
import stubs.responsiblestubs.AlreadyLoggedInResponsibleException_Exception;
import stubs.responsiblestubs.UnknownResponsibleException_Exception;

import java.util.List;

public class LoginResponsible extends Command<PEPublicAPI> {

    private String email;
    private String password;
    public static String loggedInResponsibleId;

    @Override
    public String identifier() {
        return "login_responsible";
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
            shell.system.responsibleService.loginResponsible(email, password);
            loggedInResponsibleId = email;
        } catch (AlreadyLoggedInResponsibleException_Exception e) {
            System.err.println("The responsible with email '" + e.getFaultInfo().getEmail() + "' is already logged in.");
        } catch (UnknownResponsibleException_Exception e) {
            System.err.println("The responsible with email '" + e.getFaultInfo().getEmail() + "' is unknown.");
        }
    }

    @Override
    public String describe() {
        return "Login an existing Responsible in the PolyEvent system\n" +
                "	--> login_responsible <Responsible Email> <Responsible Password>";
    }

}
