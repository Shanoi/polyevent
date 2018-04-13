package fr.unice.polytech.isa.teamk.components;

import fr.unice.polytech.isa.teamk.RoomCatalogueExploration;
import fr.unice.polytech.isa.teamk.RoomCatalogueModifier;
import fr.unice.polytech.isa.teamk.RoomDispenser;
import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.entities.Room;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;

@Stateless
public class RoomDispenserBean implements RoomDispenser {

    @EJB
    private RoomCatalogueModifier roomCatalogueModifier;

    @EJB
    private RoomCatalogueExploration roomCatalogueExploration;

    public RoomDispenserBean() {

    }

    @Override
    public boolean attributeRoom(Room room, Event event, Date start, Date end) {
        return false;
    }

}
