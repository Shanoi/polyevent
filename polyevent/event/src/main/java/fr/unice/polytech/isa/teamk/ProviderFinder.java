package fr.unice.polytech.isa.teamk;


import fr.unice.polytech.isa.teamk.entities.Provider;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProviderFinder {

    List<Provider> searchProviders();

}
