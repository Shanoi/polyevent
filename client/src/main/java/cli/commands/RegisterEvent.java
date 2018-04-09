package cli.commands;

import api.PEPublicAPI;
import cli.framework.Command;

import java.util.Date;
import java.util.List;

public class RegisterEvent extends Command<PEPublicAPI> {

    private String eventName;
    private String id;
    private short startHour;
    private short startDay;
    private short startMonth;
    private short startYear;
    private short endHour;
    private short endDay;
    private short endMonth;
    private short endYear;

    @Override
    public String identifier() {
        return "create";
    }

    @Override
    public void load(List<String> args) {
        short argIndex = 0;
        eventName = args.get(argIndex++);
        id = args.get(argIndex++);


        startHour = Short.valueOf(args.get(argIndex++));
        startDay = Short.valueOf(args.get(argIndex++));
        startMonth = Short.valueOf(args.get(argIndex++));
        startYear = Short.valueOf(args.get(argIndex++));

        endHour = Short.valueOf(args.get(argIndex++));
        endDay = Short.valueOf(args.get(argIndex++));
        endMonth = Short.valueOf(args.get(argIndex++));
        endYear = Short.valueOf(args.get(argIndex));
    }

    @Override
    public void execute() throws Exception {
        shell.system.eventService.submitNewEvent(eventName, id, startDay, startMonth, startYear, endDay, endMonth, endYear);
    }

    @Override
    public String describe() {
        return "Register an event in the PolyEvent system\n" +
                "	--> create <Event name> <Organizer ID> <Start day> <Start month> <Start year> <End day> <End month> <End year>";
    }
}
