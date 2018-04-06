package fr.unice.polytech.isa.teamk;

import fr.unice.polytech.isa.teamk.entities.Room;

import javax.ejb.Local;

@Local
public interface RoomCatalogueModifier {

    boolean addRoom(Room salle);

    boolean delRoomByID(String id);

}