package fr.unice.polytech.isa.teamk.entities;

import fr.unice.polytech.isa.teamk.entities.users.User;

public class Message {

    private User destination;
    private User sender;
    private Event event;
    private String description;

    public Message(User destination, User sender, Event event, String description) {
        this.destination = destination;
        this.sender = sender;
        this.event = event;
        this.description = description;
    }

    public User getDestination() {
        return destination;
    }

    public User getSender() {
        return sender;
    }

    public Event getEvent() {
        return event;
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

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
