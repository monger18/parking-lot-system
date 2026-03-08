package org.example.billing;

import org.example.model.Ticket;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

public class HourlyFeeStrategy implements FeeStrategy {

    private BigDecimal ratePerHour;

    public HourlyFeeStrategy(BigDecimal ratePerHour) {
        this.ratePerHour = ratePerHour;
    }

    @Override
    public BigDecimal calculateFee(Ticket ticket) {
        LocalDateTime entry = ticket.getEntryTime();
        LocalDateTime exit = ticket.getExitTime();

        long duration = Duration.between(entry, exit).toHours();

        if(duration == 0) {
            duration = 1;
        }

        return ratePerHour.multiply(BigDecimal.valueOf(duration));
    }
}
