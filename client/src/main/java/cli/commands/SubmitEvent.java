package cli.commands;

import api.PEPublicAPI;
import cli.framework.Command;
import stubs.eventstubs.Event;
import stubs.eventstubs.Organizer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SubmitEvent extends Command<PEPublicAPI> {

    private Event event;

    @Override
    public String identifier() {
        return "submit_event";
    }

    @Override
    public void load(List<String> args) {
        short argIndex = 0;
        String eventName = args.get(argIndex++);

        short startHour = Short.valueOf(args.get(argIndex++));
        short startDay = Short.valueOf(args.get(argIndex++));
        short startMonth = Short.valueOf(args.get(argIndex++));
        short startYear = Short.valueOf(args.get(argIndex++));

        short endHour = Short.valueOf(args.get(argIndex++));
        short endDay = Short.valueOf(args.get(argIndex++));
        short endMonth = Short.valueOf(args.get(argIndex++));
        short endYear = Short.valueOf(args.get(argIndex++));

        int nbAttendee = Integer.valueOf(args.get(argIndex));

        Date startDate = new Date(startYear, startMonth, startDay, startHour, 0, 0);
        Date endDate = new Date(endYear, endMonth, endDay, endHour, 0, 0);

        event = new Event();
        event.setName(eventName);
    }

    @Override
    public void execute() throws Exception {
       shell.system.eventService.submitNewEvent(event, LoginOrganizer.loggedInOrganizerId);
    }

    @Override
    public String describe() {
        return "An organizer can use this command to submit a new event to the Polyevent system\n" +
                "	--> submit_event <Event name> <Number attendee> <Start hour> <Start day> <Start month> <Start year> <End hour> <End day> <End month> <End year>";
    }

}
