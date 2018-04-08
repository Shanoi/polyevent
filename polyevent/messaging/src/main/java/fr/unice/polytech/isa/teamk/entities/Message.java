package fr.unice.polytech.isa.teamk.entities;

import fr.unice.polytech.isa.teamk.entities.user.User;

public class Message {

    private User destination;
    private User sender;
    private String idEvent;
    private String description;

    public Message(User destination, User sender, String event, String description) {
        this.destination = destination;
        this.sender = sender;
        this.idEvent = event;
        this.description = description;
    }

    public User getDestination() {
        return destination;
    }

    public User getSender() {
        return sender;
    }

    public String getEvent() {
        return idEvent;
    }

    public String getDescription() {
        return description;
    }

    public void setDestination(User destination) {
        this.destination = destination;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setEvent(String event) {
        this.idEvent = event;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
