package cli.commands.organizer;

import api.PEPublicAPI;
import cli.commands.responsible.LoginResponsible;
import cli.framework.Command;
import stubs.eventstubs.Event;

import java.util.List;

public class GetEventsStatus extends Command<PEPublicAPI> {

    @Override
    public String identifier() { return "get_events_status"; }

    @Override
    public void load(List<String> args) {

    }

    @Override
    public void execute() throws Exception {
        /* if (!LoginOrganizer.loggedInOrganizerId.isEmpty()) {
            List<Event> submittedEvents = shell.system.eventService.getEventsByOrganizer(LoginOrganizer.loggedInOrganizerId);
            System.out.println("List of events with status 'SUBMITTED':");
            int i = 1;
            for (Event e : submittedEvents) {
                System.out.println(i++ + ". " + e.getName());
            }
        } else {
            System.err.println("You have to login to invoke this command.");
        } */
    }

    @Override
    public String describe() {
        return "Retrieves all the events status of a given organizer from the Polyevent system\n" +
                "	--> get_events_status";
    }

}
