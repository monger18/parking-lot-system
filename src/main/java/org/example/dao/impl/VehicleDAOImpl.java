package org.example.dao.impl;

import org.example.dao.VehicleDAO;
import org.example.model.Vehicle;
import org.example.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class VehicleDAOImpl implements VehicleDAO {

    @Override
    public void saveVehicle(Vehicle vehicle) {

        String sql = "INSERT INTO vehicle (plate_number, vehicle_type, owner_name) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, vehicle.getPlateNumber());
            ps.setString(2, vehicle.getVehicleType());
            ps.setString(3, vehicle.getOwnerName());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error while saving vehicle",e);
        }
    }

    @Override
    public Optional<Vehicle> findVehicleByNumber(String vehicleNumber) {
        String sql = "SELECT * FROM vehicle WHERE plate_number = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, vehicleNumber);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Vehicle vehicle = new Vehicle(
                        rs.getInt("id"),
                        rs.getString("plate_number"),
                        rs.getString("vehicle_type"),
                        rs.getString("owner_name"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                );
                return Optional.of(vehicle);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching vehicle",e);
        }
    }
}
