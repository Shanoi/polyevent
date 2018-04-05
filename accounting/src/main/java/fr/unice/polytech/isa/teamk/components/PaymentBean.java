package fr.unice.polytech.isa.teamk.components;

import fr.unice.polytech.isa.teamk.Payment;
import fr.unice.polytech.isa.teamk.entities.Event;

import javax.ejb.Stateless;

@Stateless
public class PaymentBean implements Payment {

    public PaymentBean() {

    }

    @Override
    public boolean pay(float amount, Event event) {
        boolean res = true;

        if (event.integrationGetter() == 0) {
            return false;
        }

        //TODO res = bank.executeTransaction(amount, event.getResponsible());

        return res;
    }

}