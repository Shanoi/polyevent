package cli.commands;

import api.PEPublicAPI;
import cli.framework.Command;

import java.util.List;

public class SubmitEvent extends Command<PEPublicAPI> {

    private String eventName;
    private String startDate;
    private String endDate;
    private int nbAttendee;

    @Override
    public String identifier() {
        return "submit_event";
    }

    @Override
    public void load(List<String> args) {
        short argIndex = 0;
        eventName = args.get(argIndex++);

        short startHour = Short.parseShort(args.get(argIndex++));
        short startDay = Short.parseShort(args.get(argIndex++));
        short startMonth = Short.parseShort(args.get(argIndex++));
        short startYear = Short.parseShort(args.get(argIndex++));
        startDate = startHour + ":00 " + startDay + "/" + startMonth + "/" + startYear;

        short endHour = Short.parseShort(args.get(argIndex++));
        short endDay = Short.parseShort(args.get(argIndex++));
        short endMonth = Short.parseShort(args.get(argIndex++));
        short endYear = Short.parseShort(args.get(argIndex++));
        endDate = endHour + ":00 " + endDay + "/" + endMonth + "/" + endYear;

        nbAttendee = Integer.valueOf(args.get(argIndex));
    }

    @Override
    public void execute() throws Exception {
        shell.system.eventService.submitNewEvent(eventName, startDate, endDate, nbAttendee, LoginOrganizer.loggedInOrganizerId);
    }

    @Override
    public String describe() {
        return "An organizer can use this command to submit a new event to the Polyevent system\n" +
                "	--> submit_event <Event name> <Start hour> <Start day> <Start month> <Start year> <End hour> <End day> " +
                "<End month> <End year> <Number attendee>";
    }

}
