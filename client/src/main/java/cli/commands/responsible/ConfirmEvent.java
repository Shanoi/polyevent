package cli.commands.responsible;

import api.PEPublicAPI;
import cli.framework.Command;

import java.util.List;

public class ConfirmEvent extends Command<PEPublicAPI> {

    private String eventName;
    private List<String> rooms;

    @Override
    public String identifier() { return "confirm_event"; }

    @Override
    public void load(List<String> args) {
        short argIndex = 0;

        eventName = args.get(argIndex++);

        while (argIndex < args.size()) {
            rooms.add(args.get(argIndex++));
        }
    }

    @Override
    public void execute() throws Exception {
        shell.system.eventService.confirmEvent(eventName, rooms);
    }

    @Override
    public String describe() {
        return "A responsible can use this command to confirm an event to the Polyevent system\n" +
                "	--> confirm_event <Event ID>";
    }

}
