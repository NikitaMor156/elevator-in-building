package com.gmail.mor.elevator.manager;

import com.gmail.mor.elevator.entiy.Floor;
import com.gmail.mor.elevator.entiy.Passenger;

import java.util.List;

public class FloorManager {

    public static void addPassenger(Floor floor, Passenger passenger){
        floor.getPassengers().add(passenger);
    }

    public static void addPassengers(Floor floor, List<Passenger> passengerList){
        floor.getPassengers().addAll(passengerList);
    }

    public static void removePassenger(Floor floor, Passenger passenger){
        floor.getPassengers().remove(passenger);
    }

    public static void removePassenger(Floor floor, int index){
        floor.getPassengers().remove(index);
    }

    public static void removePassengers(Floor floor, List<Passenger> passengerList){
        floor.getPassengers().removeAll(passengerList);
    }

}
