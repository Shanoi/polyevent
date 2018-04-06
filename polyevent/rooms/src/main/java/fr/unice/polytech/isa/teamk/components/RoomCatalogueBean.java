package fr.unice.polytech.isa.teamk.components;

import fr.unice.polytech.isa.teamk.MaterialExploration;
import fr.unice.polytech.isa.teamk.RoomCatalogueExploration;
import fr.unice.polytech.isa.teamk.RoomCatalogueModifier;
import fr.unice.polytech.isa.teamk.RoomTypeProvider;
import fr.unice.polytech.isa.teamk.entities.Room;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class RoomCatalogueBean implements RoomCatalogueModifier, RoomCatalogueExploration, RoomTypeProvider {

    @EJB
    private MaterialExploration materialExploration;

    public RoomCatalogueBean() {

    }

    @Override
    public List<Room> getAllRooms() {
        List<Room> res = new ArrayList<>();

        return res;
    }

    @Override
    public Room getRoomByID(String id) {
        Room res;

        return new Room();
    }

    @Override
    public List<Room> getRoomByType(String type) {
        List<Room> res = new ArrayList<>();

        return res;
    }

    @Override
    public List<String> getAllRoomType() {
        return new ArrayList<>();
    }

    @Override
    public boolean addRoom(Room salle) {
        return false;
    }

    @Override
    public boolean delRoomByID(String id) {
        return false;
    }

}