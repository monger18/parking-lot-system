package org.example.strategy;

import org.example.model.ParkingSpot;
import org.example.model.Vehicle;

import java.util.*;

public class NearestSpotStrategy implements SpotAllocationStrategy {

    private Map<String, PriorityQueue<ParkingSpot>> spotMap = new HashMap<>();

    public NearestSpotStrategy(List<ParkingSpot> parkingSpots) {
        for (ParkingSpot parkingSpot : parkingSpots) {
            if(!parkingSpot.isOccupied()) {
                spotMap.computeIfAbsent(parkingSpot.getSpotType(), k->new PriorityQueue<>(new SpotDistanceComparator()))
                .add(parkingSpot);
            }
        }
    }

    @Override
    public Optional<ParkingSpot> findSpot(Vehicle vehicle) {
        PriorityQueue<ParkingSpot> queue = spotMap.get(vehicle.getVehicleType());

        if(queue == null || queue.isEmpty()) {
            return Optional.empty();
        }

        ParkingSpot spot = queue.poll();

        return Optional.of(spot);
    }

    @Override
    public void releaseSpot(ParkingSpot parkingSpot) {
        parkingSpot.setOccupied(false);
        PriorityQueue<ParkingSpot> queue = spotMap.get(parkingSpot.getSpotType());

        if(queue != null) {
            queue.add(parkingSpot);
        }
    }

}
