package fr.unice.polytech.isa.teamk.interceptors;

import fr.unice.polytech.isa.teamk.exceptions.PriceException;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class PriceVerifier {

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        int price = (int) context.getParameters()[1];

        if (price <= 0) {
            throw new PriceException("Wrong price input", null);
        }

        return context.proceed();
    }

}
