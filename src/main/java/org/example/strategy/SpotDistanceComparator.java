package org.example.strategy;

import org.example.model.ParkingSpot;

import java.util.Comparator;

public class SpotDistanceComparator implements Comparator<ParkingSpot> {

    @Override
    public int compare (ParkingSpot o1, ParkingSpot o2) {
        int floorCompare = Integer.compare(o1.getFloorId(), o2.getFloorId());

        if (floorCompare != 0) {
            return floorCompare;
        }

        return o1.getSpotNumber().compareTo(o2.getSpotNumber());
    }
}
