package fr.unice.polytech.isa.teamk.exceptions;

import java.io.Serializable;

public class RegisterEventException extends Exception implements Serializable {

    private String eventName;
    private String roomID;

    public RegisterEventException(String eventName, String roomID) {
        this.eventName = eventName;
        this.roomID = roomID;
    }

    public RegisterEventException(String eventName, String roomID, Exception source) {
        super(source);
        this.eventName = eventName;
        this.roomID = roomID;
    }

    public RegisterEventException() {
    }

    public String getName() {
        return eventName;
    }

    public void setName(String eventName) {
        this.eventName = eventName;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

}