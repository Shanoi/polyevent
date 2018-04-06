package fr.unice.polytech.isa.teamk;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RoomTypeProvider {

    List<String> getAllRoomType();

}