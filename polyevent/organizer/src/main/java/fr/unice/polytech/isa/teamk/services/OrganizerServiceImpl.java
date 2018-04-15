package fr.unice.polytech.isa.teamk.services;

import fr.unice.polytech.isa.teamk.OrganizerRegister;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyExistingOrganizer;
import fr.unice.polytech.isa.teamk.exceptions.AlreadyLoggedInOrganizer;
import fr.unice.polytech.isa.teamk.exceptions.UnknownOrganizerException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

@Stateless(name = "OrganizerWS")
@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/organizer")
public class OrganizerServiceImpl implements OrganizerService {

    /*@EJB
    private Payment payment;*/

    @EJB
    private OrganizerRegister organizerRegister;

    public OrganizerServiceImpl() {

    }

    @Override
    public void registerOrganizer(String name, String email, String password, String phone) throws AlreadyExistingOrganizer {
        organizerRegister.registerOrganizer(name, email, password, phone);
    }

    @Override
    public void loginOrganizer(String id, String password) throws UnknownOrganizerException, AlreadyLoggedInOrganizer {
        organizerRegister.loginOrganizer(id, password);
    }

    @Override
    public void sendPayment(String id, int price) {

    }

}
