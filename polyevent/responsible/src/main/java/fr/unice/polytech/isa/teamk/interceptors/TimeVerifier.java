package fr.unice.polytech.isa.teamk.interceptors;

import fr.unice.polytech.isa.teamk.exceptions.TimeException;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeVerifier {

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        LocalDateTime startDate;
        LocalDateTime endDate;
        if (context.getMethod().getName().equals("submitNewEvent")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm d/M/yyyy");
            startDate = LocalDateTime.parse((String) context.getParameters()[1], formatter);
            endDate = LocalDateTime.parse((String) context.getParameters()[2], formatter);
            checkDates(startDate, endDate);
        } else if (context.getMethod().getName().equals("getVacantRooms")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm_d-M-yyyy");
            startDate = LocalDateTime.parse((String) context.getParameters()[0], formatter);
            endDate = LocalDateTime.parse((String) context.getParameters()[1], formatter);
            checkDates(startDate, endDate);
        }

        return context.proceed();
    }

    private void checkDates(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate) || startDate.isBefore(LocalDateTime.now())) {
            throw new TimeException("Inconsistent dates", null);
        }
    }

}
