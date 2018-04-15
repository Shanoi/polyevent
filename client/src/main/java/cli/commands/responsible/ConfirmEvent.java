package cli.commands.responsible;

import api.PEPublicAPI;
import cli.framework.Command;

import java.util.List;

public class ConfirmEvent extends Command<PEPublicAPI> {

    private String eventId;

    @Override
    public String identifier() { return "confirm_event"; }

    @Override
    public void load(List<String> args) {
        short argIndex = 0;

        eventId = args.get(argIndex);
    }

    @Override
    public void execute() throws Exception {
        // confirm event
    }

    @Override
    public String describe() {
        return "An responsible can use this command to confirm an event to the Polyevent system\n" +
                "	--> confirm_event <Event ID>";
    }

}
