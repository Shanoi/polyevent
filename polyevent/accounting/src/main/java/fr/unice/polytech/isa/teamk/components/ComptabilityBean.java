package fr.unice.polytech.isa.teamk.components;

import fr.unice.polytech.isa.teamk.Payment;
import fr.unice.polytech.isa.teamk.QuotationEstablisher;
import fr.unice.polytech.isa.teamk.entities.Event;
import fr.unice.polytech.isa.teamk.entities.Provider;
import fr.unice.polytech.isa.teamk.entities.Quote;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ComptabilityBean implements Payment, QuotationEstablisher {

    public ComptabilityBean() {

    }

    @Override
    public boolean pay(float amount, Event event) {
        return false;
    }

    @Override
    public Quote createQuote(List<Provider> providerList) {
        return new Quote();
    }

}