package fr.unice.polytech.isa.teamk.external;

import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.entities.Room;
import fr.unice.polytech.isa.teamk.entities.RoomType;
import fr.unice.polytech.isa.teamk.exceptions.ExternalPartnerException;
import org.apache.cxf.jaxrs.client.WebClient;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.core.MediaType;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class CalendarService {

    private String url;

    public CalendarService(String host, String port) {
        this.url = "http://" + host + ":" + port + "/calendar";
    }

    public CalendarService() {
        this("localhost", "9090");
    }

    /**
     * JAXB doesn't handle interfaces this is why the return type is an HashMap of String mapped with ArrayList.
     */
    public HashMap<String, ArrayList<Room>> getVacantRooms(String startDate, String endDate) throws ExternalPartnerException {
        JSONArray vacantRooms;
        try {
            String response = WebClient.create(url).path("/rooms/" + startDate + "/" + endDate).get(String.class);
            vacantRooms = new JSONArray(response);
        } catch (Exception e) {
            throw new ExternalPartnerException(url + "/rooms/" + startDate + "/" + endDate, e);
        }

        HashMap<String, ArrayList<Room>> slots = new LinkedHashMap<>();
        ArrayList<Room> rooms = new ArrayList<>();

        // Process response
        for (int i = 0; i < vacantRooms.length(); ++i) {
            JSONObject slot = vacantRooms.getJSONObject(i);
            String slotKey = slot.getString("Key");
            JSONArray values = slot.getJSONArray("Value");
            for (int j = 0; j < values.length(); ++j) {
                JSONObject jsonObjectRoom = values.getJSONObject(i);
                Room room = new Room(
                        jsonObjectRoom.getString("ID"),
                        jsonObjectRoom.getInt("Capacity"),
                        RoomType.convert(jsonObjectRoom.getInt("Type")));
                rooms.add(room);
            }
            slots.put(slotKey, rooms);
        }

        return slots;
    }

    public boolean submitEvent(Event event) throws ExternalPartnerException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        String startDate = event.getStartingDate().toLocalDateTime().format(formatter);
        String endDate = event.getEndingDate().toLocalDateTime().format(formatter);

        JSONObject request = new JSONObject().put("StartDate", startDate).put("EndDate", endDate);

        Integer id;

        try {
            String str = WebClient.create(url).path("/eventbox")
                    .accept(MediaType.APPLICATION_JSON_TYPE)
                    .header("Content-Type", MediaType.APPLICATION_JSON)
                    .post(request.toString(), String.class);
            id = Integer.parseInt(str);
        } catch (Exception e) {
            throw new ExternalPartnerException(url + "/eventbox", e);
        }

        JSONObject status;

        try {
            String response = WebClient.create(url).path("/events/" + id).get(String.class);
            status = new JSONObject(response);
        } catch (Exception e) {
            throw new ExternalPartnerException(url + "events/" + id, e);
        }
        // Assessing the payment status
        return (status.getInt("Status") == 0);
    }

}
