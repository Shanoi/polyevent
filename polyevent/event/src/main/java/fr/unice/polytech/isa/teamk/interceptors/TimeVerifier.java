package fr.unice.polytech.isa.teamk.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.io.UncheckedIOException;
import java.time.LocalDate;

public class TimeVerifier {

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        LocalDate start = LocalDate.of((short) context.getParameters()[4], (short) context.getParameters()[3], (short) context.getParameters()[2]);
        LocalDate end = LocalDate.of((short) context.getParameters()[7], (short) context.getParameters()[6], (short) context.getParameters()[5]);

        if (start.isAfter(end)) {
            throw new UncheckedIOException("Start date can't be after end date", null);
        }

        return context.proceed();
    }

}
