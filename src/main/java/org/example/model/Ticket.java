package org.example.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Ticket {
    private int id;
    private int vehicleId;
    private int spotId;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private String status;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;

    public Ticket() {}

    public Ticket(int id, int vehicleId, int spotId, LocalDateTime entryTime, LocalDateTime exitTime, String status, BigDecimal totalAmount, LocalDateTime createdAt) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.spotId = spotId;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.status = status;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getSpotId() {
        return spotId;
    }
    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }
    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }
    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
