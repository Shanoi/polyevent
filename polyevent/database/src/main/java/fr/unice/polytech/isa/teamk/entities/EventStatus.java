package fr.unice.polytech.isa.teamk.entities;

import java.util.Optional;

public enum EventStatus {

    SUBMITTED, CONFIRMED, PAID, RUNNING, FINISHED;

    public static Optional<EventStatus> next(EventStatus status) {
        Optional<EventStatus> result = Optional.empty();
        switch (status) {
            case SUBMITTED:
                result = Optional.of(CONFIRMED);
                break;
            case CONFIRMED:
                result = Optional.of(PAID);
                break;
            case PAID:
                result = Optional.of(RUNNING);
                break;
            case RUNNING:
                result = Optional.of(FINISHED);
                break;
            default:
        }
        return result;
    }

}
