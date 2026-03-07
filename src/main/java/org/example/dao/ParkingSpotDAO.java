package org.example.dao;

import org.example.model.ParkingSpot;

import java.util.Optional;

public interface ParkingSpotDAO {
    Optional<ParkingSpot> findAvailableSpot(String spotType);

    void occupySpot(int spotId);
    void freeSpot(int spotId);
}
