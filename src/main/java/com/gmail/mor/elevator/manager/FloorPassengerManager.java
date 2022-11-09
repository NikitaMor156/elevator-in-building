package com.gmail.mor.elevator.manager;

import com.gmail.mor.elevator.entiy.Floor;
import com.gmail.mor.elevator.entiy.Passenger;
import lombok.Data;

import java.util.List;

//This class is created to do operations of adding/removing
//passengers to/from floor (Floor object's passenger list).
@Data
public class FloorPassengerManager {

    //Add passenger to the defined floor (it's floor list).
    public static void addPassenger(Floor floor, Passenger passenger) {
        floor.getPassengers().add(passenger);
    }

    //Add passengers from list to the defined floor (it's floor list).
    public static void addPassengers(Floor floor, List<Passenger> passengerList) {
        floor.getPassengers().addAll(passengerList);
    }

    //Add passenger from defined floor (it's floor list).
    public static void removePassenger(Floor floor, Passenger passenger) {
        floor.getPassengers().remove(passenger);
    }

    //Add passenger from defined floor (it's floor list) by index.
    public static void removePassenger(Floor floor, int index) {
        floor.getPassengers().remove(index);
    }

    //Add passengers from defined floor (it's floor list).
    public static void removePassengers(Floor floor, List<Passenger> passengerList) {
        floor.getPassengers().removeAll(passengerList);
    }

}
