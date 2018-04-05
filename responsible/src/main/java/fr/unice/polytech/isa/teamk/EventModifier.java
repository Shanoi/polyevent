package fr.unice.polytech.isa.teamk;

import java.util.Date;

public interface EventModifier {

    boolean modifyNbAttendee(int idEvent, int nbAttendee);

    boolean modifyStartDate(int idEvent, Date dateStart);

    boolean modifyEndDate(int idEvent, Date dateEnd);

    boolean modifyNeedMaterial(int idEvent, String typeMaterial, int materialQuantity);

    boolean modifyNeedMaterial(String typeRoom, String typeMaterial, int materialQuantity);

    boolean addService(int idEvent, String providerType);

    boolean delNeedMaterial(int idEvent, String materialType);

    boolean delService(int idEvent, String providerType);

}