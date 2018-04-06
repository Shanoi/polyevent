package fr.unice.polytech.isa.teamk;

import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.entities.Room;

import java.util.Date;

public interface RoomDispenser {

    boolean attributeRoom(Room room, Event event, Date start, Date end);

}
