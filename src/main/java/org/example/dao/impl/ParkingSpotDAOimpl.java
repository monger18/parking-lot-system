package org.example.dao.impl;

import org.example.dao.ParkingSpotDAO;
import org.example.model.ParkingSpot;
import org.example.util.DBConnection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ParkingSpotDAOimpl implements ParkingSpotDAO {

    @Override
    public Optional<ParkingSpot> findAvailableSpot(String spotType) {
        String sql = """
                    Select * 
                    from ParkingSpot 
                    where spotType = ? 
                    and is_Occupied = false 
                    Order by id 
                    LIMIT 1 
                    FOR UPDATE SKIP LOCKED
                    """;
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, spotType);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                ParkingSpot spot = new ParkingSpot(
                        rs.getInt("id"),
                        rs.getInt("floor_id"),
                        rs.getString("spot_number"),
                        rs.getString("spot_type"),
                        rs.getBoolean("is_occupied"),
                        rs.getInt("version"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                );
                return Optional.of(spot);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Available spots not found!", e);
        }
        return Optional.empty();
    }

    @Override
    public void occupySpot(int spotId) {
        String sql = "UPDATE ParkingSpot SET is_Occupied = true WHERE id = ?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, spotId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Occupy spots not found!", e);
        }
    }

    @Override
    public void freeSpot(int spotId) {
        String sql = "UPDATE ParkingSpot SET is_Occupied = false WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, spotId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Freeing spots not found!", e);
        }
    }

}
