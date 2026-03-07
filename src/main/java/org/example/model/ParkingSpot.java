package org.example.model;

import java.time.LocalDateTime;

public class ParkingSpot {
    private int id;
    private int floorId;
    private String spotNumber;
    private String spotType;
    private boolean isOccupied;
    private int version;
    private LocalDateTime createdAt;

    public ParkingSpot() {}

    public ParkingSpot(int id, int floorId, String spotNumber, String spotType, boolean isOccupied, int version, LocalDateTime createdAt) {
        this.id = id;
        this.floorId = floorId;
        this.spotNumber = spotNumber;
        this.spotType = spotType;
        this.isOccupied = isOccupied;
        this.version = version;
        this.createdAt = createdAt;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public int getFloorId() {return floorId;}
    public void setFloorId(int floorId) {this.floorId = floorId;}

    public String getSpotNumber() {return spotNumber;}
    public void setSpotNumber(String spotNumber) {this.spotNumber = spotNumber;}

    public String getSpotType() {return spotType;}
    public void setSpotType(String spotType) {this.spotType = spotType;}

    public boolean isOccupied() {return isOccupied;}
    public void setOccupied(boolean occupied) {isOccupied = occupied;}

    public int getVersion() {return version;}
    public void setVersion(int version) {this.version = version;}

    public LocalDateTime getCreatedAt() {return createdAt;}
    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}

}
