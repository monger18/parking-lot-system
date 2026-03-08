package org.example.billing;

import org.example.model.Ticket;

import java.math.BigDecimal;

public interface FeeStrategy {
    BigDecimal calculateFee(Ticket ticket);
}
