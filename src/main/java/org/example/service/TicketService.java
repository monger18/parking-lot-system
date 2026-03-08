package org.example.service;

import org.example.billing.FeeStrategy;
import org.example.model.Ticket;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TicketService {

    private FeeStrategy feeStrategy;

    public TicketService(FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public BigDecimal closeTicket(Ticket ticket) {
        ticket.setExitTime(LocalDateTime.now());
        ticket.setStatus("COMPLETED");

        return feeStrategy.calculateFee(ticket);
    }
}
