package org.example.model;

import java.time.LocalDateTime;

public class Reservation {
    private int id;
    private int vehicleId;
    private int spotId;
    private LocalDateTime reservedFrom;
    private LocalDateTime reservedUntil;
    private String status;
    private LocalDateTime createdAt;

    public Reservation() {}

    public Reservation(int id, int vehicleId, int spotId,  LocalDateTime reservedFrom, LocalDateTime reservedUntil, String status, LocalDateTime createdAt) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.spotId = spotId;
        this.reservedFrom = reservedFrom;
        this.reservedUntil = reservedUntil;
        this.status = status;
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

    public LocalDateTime getReservedFrom() {
        return reservedFrom;
    }
    public void setReservedFrom(LocalDateTime reservedFrom) {
        this.reservedFrom = reservedFrom;
    }

    public LocalDateTime getReservedUntil() {
        return reservedUntil;
    }
    public void setReservedUntil(LocalDateTime reservedUntil) {
        this.reservedUntil = reservedUntil;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
