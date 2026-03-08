package org.example.strategy;

import org.example.model.ParkingSpot;
import org.example.model.Vehicle;

import java.util.Optional;

public interface SpotAllocationStrategy {
    Optional<ParkingSpot> findSpot(Vehicle vehicle);

    void releaseSpot(ParkingSpot parkingSpot);

}
