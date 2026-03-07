package org.example.model;

import java.time.LocalDateTime;

public class ParkingFloor {
    private int id;
    private int floorNumber;
    private int totalSpots;
    private LocalDateTime createdAt;

    public ParkingFloor(){}

    public ParkingFloor(int id, int floorNumber, int totalSpots, LocalDateTime createdAt) {
        this.id = id;
        this.floorNumber = floorNumber;
        this.totalSpots = totalSpots;
        this.createdAt = createdAt;

    }

    public int getId() { return id;}
    public void setId(int id) { this.id = id; }

    public int getFloorNumber() { return floorNumber; }
    public void setFloorNumber(int floorNumber) { this.floorNumber = floorNumber; }

    public int getTotalSpots() { return totalSpots; }
    public void setTotalSpots(int totalSpots) { this.totalSpots = totalSpots; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
