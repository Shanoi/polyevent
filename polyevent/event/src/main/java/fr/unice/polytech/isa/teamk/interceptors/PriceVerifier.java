package fr.unice.polytech.isa.teamk.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.io.UncheckedIOException;

public class PriceVerifier {

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        int price = (int) context.getParameters()[1];

        if (price <= 0) {
            throw new UncheckedIOException("Wrong price input", null);
        }

        return context.proceed();

    }

}
