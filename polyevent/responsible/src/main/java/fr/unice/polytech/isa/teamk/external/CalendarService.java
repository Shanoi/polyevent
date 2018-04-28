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
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CalendarService {

    private String url;

    private static final Logger log = Logger.getLogger(Logger.class.getName());

    public CalendarService(String host, String port) {
        this.url = "http://" + host + ":" + port + "/calendar";
    }

    public CalendarService() {
        this("localhost", "9090");
    }

    /**
     * JAXB doesn't handle interfaces this is why the return type is an HashMap of String mapped with ArrayList.
     */
    public HashMap<String, Room[]> getVacantRooms(String startDate, String endDate) throws ExternalPartnerException {
        JSONArray vacantRooms;

        try {
            String response = WebClient.create(url).path("/rooms/" + startDate + "/" + endDate).get(String.class);
            log.log(Level.SEVERE, "getVacantRooms external Service response\n{0}\n", response);
            vacantRooms = new JSONArray(response);
        } catch (Exception e) {
            throw new ExternalPartnerException(url + "/rooms/" + startDate + "/" + endDate, e);
        }

        HashMap<String, Room[]> slots = new HashMap<>();


        // Process response
        for (int i = 0; i < vacantRooms.length(); ++i) {
            JSONObject slot = vacantRooms.getJSONObject(i);
            String slotKey = slot.getString("Key");
            JSONArray values = slot.getJSONArray("Value");
            Room[] rooms = new Room[values.length()];
            for (int j = 0; j < values.length(); ++j) {
                JSONObject jsonObjectRoom = values.getJSONObject(j);
                Room room = new Room(
                        jsonObjectRoom.getString("ID"),
                        jsonObjectRoom.getInt("Capacity"),
                        RoomType.convert(jsonObjectRoom.getInt("Type")));
                rooms[j] = room;
            }
            slots.put(slotKey, rooms);
        }

        return slots;
    }

    public boolean confirmEvent(Event event, List<String> room) throws ExternalPartnerException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm d/M/yyyy");
        String startDate = event.getStartingDate().toLocalDateTime().format(formatter);
        String endDate = event.getEndingDate().toLocalDateTime().format(formatter);

        JSONObject request = new JSONObject().put("StartDate", startDate).put("EndDate", endDate).put("Rooms", room);

        Integer id;

        log.log(Level.SEVERE, "Event Request: {0}", request);

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

    public Room roomInfo(String roomID) throws ExternalPartnerException {
        JSONObject roomInfo;

        try {
            String response = WebClient.create(url).path("/room/" + roomID).get(String.class);
            roomInfo = new JSONObject(response);
        } catch (Exception e) {
            throw new ExternalPartnerException(url + "/room/" + roomID, e);
        }

        int capacity = roomInfo.getInt("Capacity");
        RoomType type = RoomType.convert(roomInfo.getInt("Type"));

        return new Room(roomID, capacity, type);
    }

}
