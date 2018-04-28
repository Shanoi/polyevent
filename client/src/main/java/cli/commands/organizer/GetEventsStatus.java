package cli.commands.organizer;

import api.PEPublicAPI;
import cli.framework.Command;
import stubs.eventstubs.GetEventsByOrganizerResponse;

import java.util.List;

public class GetEventsStatus extends Command<PEPublicAPI> {

    @Override
    public String identifier() {
        return "get_events_status";
    }

    @Override
    public void load(List<String> args) {

    }

    @Override
    public void execute() throws Exception {
        if (!LoginOrganizer.loggedInOrganizerId.isEmpty()) {
            List<GetEventsByOrganizerResponse.Return.Entry> submittedEvents = shell.system.eventService.
                    getEventsByOrganizer(LoginOrganizer.loggedInOrganizerId).getEntry();
            System.out.println("List of events with status 'SUBMITTED':");
            int i = 1;
            for (GetEventsByOrganizerResponse.Return.Entry e : submittedEvents) {
                System.out.println(i++ + ". " + e.getKey() + ", Status: " + e.getValue().name());
            }
        } else {
            System.err.println("You have to login to invoke this command.");
        }
    }

    @Override
    public String describe() {
        return "Retrieves all the events status of a given organizer from the Polyevent system\n" +
                "	--> get_events_status";
    }

}
