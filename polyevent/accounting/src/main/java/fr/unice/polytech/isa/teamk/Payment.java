package fr.unice.polytech.isa.teamk;

import fr.unice.polytech.isa.teamk.entities.Event;

import javax.ejb.Local;

@Local
public interface Payment {

    boolean pay(float amount, Event event);

}
