package cli.commands;

import api.PEPublicAPI;
import cli.framework.Command;

import java.util.List;

public class RegisterEvent extends Command<PEPublicAPI> {

	private String eventName;
	private String id;
	private short startDay;
	private short startMonth;
	private short startYear;
	private short endDay;
	private short endMonth;
	private short endYear;


	@Override
	public String identifier() { return "create"; }

	@Override
	public void load(List<String> args) {
		eventName = args.get(0);
		id = args.get(1);

		startDay = Short.valueOf(args.get(2));
		startMonth = Short.valueOf(args.get(3));
		startYear = Short.valueOf(args.get(4));

		endDay = Short.valueOf(args.get(5));
		endMonth = Short.valueOf(args.get(6));
		endYear = Short.valueOf(args.get(7));
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
