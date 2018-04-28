package cli.commands.responsible;

import api.PEPublicAPI;
import cli.framework.Command;
import stubs.eventstubs.Event;

import java.util.List;

public class GetSubmittedEvents extends Command<PEPublicAPI> {

    @Override
    public String identifier() { return "get_submitted_events"; }

    @Override
    public void load(List<String> args) {

    }

    @Override
    public void execute() throws Exception {
        if (!LoginResponsible.loggedInResponsibleId.isEmpty()) {
            List<Event> submittedEvents = shell.system.eventService.getSubmittedEvents();
            System.out.println("List of events with status 'SUBMITTED':");
            int i = 1;
            for (Event e : submittedEvents) {
                System.out.println(i++ + ". " + e.getName() + " (ID: " + e.getId() + ")");
            }
        } else {
            System.err.println("You have to login to invoke this command.");
        }
    }

    @Override
    public String describe() {
        return "Retrieves all the events with status 'SUBMITTED' from the Polyevent system\n" +
                "	--> get_submitted_events";
    }

}
