package org.example.strategy;

import org.example.dao.ParkingSpotDAO;
import org.example.model.ParkingSpot;
import org.example.model.Vehicle;

import java.util.Optional;

public class FirstAvailableStrategy implements SpotAllocationStrategy {
    private ParkingSpotDAO parkingSpotDAO;

    public FirstAvailableStrategy(ParkingSpotDAO parkingSpotDAO) {
        this.parkingSpotDAO = parkingSpotDAO;
    }

    @Override
    public Optional<ParkingSpot> findSpot(Vehicle vehicle) {
        return parkingSpotDAO.findAvailableSpot(vehicle.getVehicleType());
    }

}
