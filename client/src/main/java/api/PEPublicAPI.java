package api;

import stubs.eventstubs.EventService;
import stubs.eventstubs.EventServiceImplService;
import stubs.organizerstubs.OrganizerService;
import stubs.organizerstubs.OrganizerServiceImplService;
import stubs.responsiblestubs.ResponsibleService;
import stubs.responsiblestubs.ResponsibleServiceImplService;

import javax.xml.ws.BindingProvider;
import java.net.URL;

public class PEPublicAPI {

    public EventService eventService;
    public OrganizerService organizerService;
    public ResponsibleService responsibleService;

    public PEPublicAPI(String host, String port) {
        initEvent(host, port);
        initOrganizer(host, port);
        initResponsible(host, port);
    }

    private void initEvent(String host, String port) {
        URL wsdlLocation = PEPublicAPI.class.getResource("/EventWS.wsdl");
        EventServiceImplService factory = new EventServiceImplService(wsdlLocation);
        this.eventService = factory.getEventServiceImplPort();
        String address = "http://" + host + ":" + port + "/event/webservices/EventWS";
        ((BindingProvider) eventService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

    private void initOrganizer(String host, String port) {
        URL wsdlLocation = PEPublicAPI.class.getResource("/OrganizerWS.wsdl");
        OrganizerServiceImplService factory = new OrganizerServiceImplService(wsdlLocation);
        this.organizerService = factory.getOrganizerServiceImplPort();
        String address = "http://" + host + ":" + port + "/event/webservices/OrganizerWS";
        ((BindingProvider) organizerService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

    private void initResponsible(String host, String port) {
        URL wsdlLocation = PEPublicAPI.class.getResource("/ResponsibleWS.wsdl");
        ResponsibleServiceImplService factory = new ResponsibleServiceImplService(wsdlLocation);
        this.responsibleService = factory.getResponsibleServiceImplPort();
        String address = "http://" + host + ":" + port + "/event/webservices/ResponsibleWS";
        ((BindingProvider) responsibleService).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, address);
    }

}
