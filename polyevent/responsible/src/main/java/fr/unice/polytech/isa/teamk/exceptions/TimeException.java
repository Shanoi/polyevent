package fr.unice.polytech.isa.teamk.exceptions;

public class TimeException extends RuntimeException {

    public TimeException(Exception e) {
        super(e);
    }

    public TimeException(String msg, Exception e) {
        super(msg, e);
    }

}
