package fr.unice.polytech.isa.teamk.exceptions;

public class PriceException extends RuntimeException {

    public PriceException(Exception e) {
        super(e);
    }

    public PriceException(String msg, Exception e) {
        super(msg, e);
    }

}
