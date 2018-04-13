package fr.unice.polytech.isa.teamk;

import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.entities.Provider;

public interface EventModifier {

    void forwardNewEvent(Event event);

    boolean forwardNewAttendeeNb(Event event);

    boolean forwardNewStartDate(Event event);

    boolean forwardNewEndDate(Event event);

    boolean forwardNewMaterial(Event event, String materialType);

    boolean forwardNewService(Event event, Provider provider);

    boolean forwardDelService(Event event, String providerType);

    boolean forwardDelMaterial(Event event, String materialType);

}
