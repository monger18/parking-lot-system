package org.example.dao.impl;

import org.example.dao.TicketDAO;
import org.example.model.Ticket;
import org.example.util.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class TicketDAOImpl implements TicketDAO {

    @Override
    public int createTicket(Ticket ticket) {
        String sql = """
                INSERT INTO ticket (vehicle_id, spot_id, entry_time, status)
                VALUES (?, ?, ?, ?)
                RETURNING id;
                """;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ticket.getVehicleId());
            ps.setInt(2, ticket.getSpotId());
            ps.setTimestamp(3, Timestamp.valueOf(ticket.getEntryTime()));
            ps.setString(4, "Active");

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Not able to create a Ticket",e);
        }
        return -1;
    }

    @Override
    public Optional<Ticket> findActiveTicketByVehicleId(int vehicleId) {
        String sql = """
                SELECT * FROM ticket WHERE vehicle_id = ?
                AND status = 'ACTIVE';
        """;
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vehicleId);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Ticket ticket = new Ticket(
                        rs.getInt("id"),
                        rs.getInt("vehicle_id"),
                        rs.getInt("spot_id"),
                        rs.getTimestamp("entry_time").toLocalDateTime(),
                        rs.getTimestamp("exit_time") != null ? rs.getTimestamp("exit_time").toLocalDateTime() : null,
                        rs.getString("status"),
                        rs.getBigDecimal("total_amount"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                );
                        return Optional.of(ticket);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Not able to find a ticket by vehicleId",e);
        }

        return Optional.empty();
    }

    @Override
    public void closeTicket(int ticketId, double totalAmount) {

        String sql = """
                UPDATE ticket
                SET exit_time = ?, total_amount = ?, status = 'CLOSED'
                WHERE id = ?
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setTimestamp(1, Timestamp.valueOf(java.time.LocalDateTime.now()));
            ps.setDouble(2, totalAmount);
            ps.setInt(3, ticketId);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Not able to close a Ticket",e);
        }
    }

}
