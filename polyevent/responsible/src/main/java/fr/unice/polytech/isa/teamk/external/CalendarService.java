package fr.unice.polytech.isa.teamk.external;

import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.exceptions.ExternalPartnerException;
import org.apache.cxf.jaxrs.client.WebClient;
import org.json.JSONObject;

import javax.ws.rs.core.MediaType;
import java.util.GregorianCalendar;

public class CalendarService {

    private String url;

    public CalendarService(String host, String port) {
        this.url = "http://" + host + ":" + port;
    }

    public CalendarService() {
        this("localhost", "9090");
    }

    public boolean submitEvent(Event event, String roomID) throws ExternalPartnerException {
        JSONObject request = new JSONObject().put("StartDate", event.getStartingDate().getTime())
                .put("EndDate", event.getEndingDate().getTime())
                .put("RoomID", roomID);

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
