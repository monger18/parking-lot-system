package org.example.dao;

import org.example.model.Vehicle;

import java.util.Optional;

public interface VehicleDAO {
    void saveVehicle(Vehicle vehicle);

    Optional<Vehicle> findVehicleByNumber(String vehicleNumber);
}
