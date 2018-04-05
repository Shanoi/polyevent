package cli.commands;

import api.PEPublicAPI;
import cli.framework.Command;

public class Bye extends Command<PEPublicAPI> {

	@Override
	public String identifier() { return "bye"; }

	@Override
	public void execute() { }

	@Override
	public String describe() {
		return "Exit PolyEvent";
	}

	@Override
	public boolean shouldContinue() { return false; }

}
