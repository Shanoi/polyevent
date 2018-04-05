package fr.unice.polytech.isa.teamk.services;

import fr.unice.polytech.isa.teamk.OrganizerRegister;
import fr.unice.polytech.isa.teamk.Payment;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyExistingOrganizer;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

@Stateless(name = "OrganizerWS")
@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/organizer")
public class OrganizerServiceImpl implements OrganizerService {

    @EJB
    private Payment payment;

    @EJB
    private OrganizerRegister organizerRegister;

    public OrganizerServiceImpl() {

    }

    @Override
    public void sendPayment(String id, int price) {

    }

    @Override
    public void registerOrganizer(String id, String password) throws AlreadyExistingOrganizer {
        organizerRegister.registerOrganizer(id, password);
    }

}