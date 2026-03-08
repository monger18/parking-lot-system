package org.example.dao;

import org.example.model.Ticket;

import java.util.Optional;

public interface TicketDAO {
    int createTicket(Ticket ticket);

    Optional<Ticket> findActiveTicketByVehicleId(int vehicleId);

    void closeTicket(int ticketId, double totalAmount);
}