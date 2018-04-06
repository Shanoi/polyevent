package fr.unice.polytech.isa.teamk;

import fr.unice.polytech.isa.teamk.entities.Room;

import java.util.List;

public interface RoomCatalogueExploration {

    List<Room> getAllRooms();

    Room getRoomByID(String id);

    List<Room> getRoomByType(String type);

}