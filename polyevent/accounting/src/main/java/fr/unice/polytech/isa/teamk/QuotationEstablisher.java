package fr.unice.polytech.isa.teamk;

import fr.unice.polytech.isa.teamk.entities.Provider;
import fr.unice.polytech.isa.teamk.entities.Quote;

import java.util.List;

public interface QuotationEstablisher {

    Quote createQuote(List<Provider> providerList);

}
