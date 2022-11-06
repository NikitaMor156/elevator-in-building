package com.gmail.mor.elevator.manager;

import com.gmail.mor.elevator.entiy.Building;
import com.gmail.mor.elevator.entiy.Floor;
import com.gmail.mor.elevator.entiy.Passenger;

import java.util.ArrayList;
import java.util.List;

public class BuildingManager {

    //Returns true if all passengers floor position is equal to destination.
    //That means that all passengers which are in the building used to get to their
    //destination floors and elevator is empty.
    //Otherwise, returns false.
    public static boolean areAllPassengersOnTheirDestinationFloors(List<Floor> floorList) {
        for (Floor floor : floorList) {
            for (Passenger p : floor.getPassengers()) {
                if (p.getDestinationFloor() != floor.getNumber()) {
                    return false;
                }
            }
        }
        return true;
    }

    //Returns true if all passengers floor position is equal to destination.
    //That means that all passengers which are in the building used to get to their
    //destination floors and elevator is empty.
    //Otherwise, returns false.
    public static boolean areAllPassengersOnTheirDestinationFloors(Building building) {
        return areAllPassengersOnTheirDestinationFloors(building.getFloors());
    }

}
