package cli.commands.responsible;

import api.PEPublicAPI;
import cli.framework.Command;
import stubs.responsiblestubs.GetVacantRoomsResponse;
import stubs.responsiblestubs.Room;

import java.util.List;

public class GetVacantRooms extends Command<PEPublicAPI> {

    private String startDate;
    private String endDate;

    @Override
    public String identifier() {
        return "get_vacant_rooms";
    }

    @Override
    public void load(List<String> args) {
        short argIndex = 0;

        short startHour = Short.parseShort(args.get(argIndex++));
        short startDay = Short.parseShort(args.get(argIndex++));
        short startMonth = Short.parseShort(args.get(argIndex++));
        short startYear = Short.parseShort(args.get(argIndex++));
        startDate = startHour + ":00_" + startDay + "-" + startMonth + "-" + startYear;

        short endHour = Short.parseShort(args.get(argIndex++));
        short endDay = Short.parseShort(args.get(argIndex++));
        short endMonth = Short.parseShort(args.get(argIndex++));
        short endYear = Short.parseShort(args.get(argIndex));
        endDate = endHour + ":00_" + endDay + "-" + endMonth + "-" + endYear;
    }

    @Override
    public void execute() throws Exception {
        if (!LoginResponsible.loggedInResponsibleId.isEmpty()) {
            List<GetVacantRoomsResponse.Return.Entry> slots = shell.system.responsibleService.getVacantRooms(startDate, endDate).getEntry();
            System.out.println("List of vacant rooms from " + startDate + " to " + endDate + ":");
            int i = 1;
            int j = 1;
            for (GetVacantRoomsResponse.Return.Entry slot : slots) {
                System.out.println(i + ". " + slot.getKey());
                for (Room room : slot.getValue().getItem()) {
                    System.out.println("\t" + i + "." + j++ + " Room " + room.getName() +
                            " (" + room.getType().name() + ")" + "(Capacity: " + room.getCapacity() + ")");
                }
                i++;
                j = 1;
            }
        } else {
            System.err.println("You have to login to invoke this command.");
        }
    }

    @Override
    public String describe() {
        return "Retrieves all the vacant rooms from the Calendar service\n" +
                "	--> get_vacant_rooms <Start hour> <Start day> <Start month> <Start year> " +
                "<End hour> <End day> <End month> <End year>";
    }

}
