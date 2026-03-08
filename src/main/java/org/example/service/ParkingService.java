package org.example.service;

import org.example.dao.ParkingSpotDAO;
import org.example.dao.TicketDAO;
import org.example.dao.VehicleDAO;
import org.example.model.ParkingSpot;
import org.example.model.Ticket;
import org.example.model.Vehicle;
import org.example.strategy.SpotAllocationStrategy;

import java.time.LocalDateTime;
import java.util.Optional;

public class ParkingService {
    private VehicleDAO vehicleDAO;
    private ParkingSpotDAO parkingSpotDAO;
    private TicketDAO ticketDAO;

    private SpotAllocationStrategy allocationStrategy;

    public ParkingService(VehicleDAO vehicleDAO, ParkingSpotDAO parkingSpotDAO, TicketDAO ticketDAO, SpotAllocationStrategy allocationStrategy) {
        this.vehicleDAO = vehicleDAO;
        this.parkingSpotDAO = parkingSpotDAO;
        this.ticketDAO = ticketDAO;
        this.allocationStrategy = allocationStrategy;
    }

    public Ticket parkVehicle(Vehicle vehicle) {

        // Save Vehicle
        vehicleDAO.saveVehicle(vehicle);

        //Find Parking Spot using Strategy
        Optional<ParkingSpot> optionalSpot = allocationStrategy.findSpot(vehicle);

        if(optionalSpot.isEmpty()) {
            throw new RuntimeException("Parking Full");
        }

        ParkingSpot spot = optionalSpot.get();

        //Mark Spot occupied
        parkingSpotDAO.occupySpot(spot.getId());

        // Create Ticket
        Ticket ticket = new Ticket();
        ticket.setVehicleId(vehicle.getId());
        ticket.setSpotId(spot.getId());
        ticket.setEntryTime(LocalDateTime.now());
        ticket.setStatus("Active");

        // Save Ticket
        ticketDAO.createTicket(ticket);

        return ticket;
    }
}
