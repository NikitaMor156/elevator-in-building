package com.gmail.mor.elevator.manager;

import com.gmail.mor.elevator.entiy.Elevator;
import com.gmail.mor.elevator.entiy.Passenger;

import java.util.List;

public class ElevatorManager {

    public static void addPassenger(Elevator elevator, Passenger passenger) {
        elevator.getPassengerList().add(passenger);
    }

    public static void addPassengers(Elevator elevator, List<Passenger> passengerList) {
        elevator.getPassengerList().addAll(passengerList);
    }

    public static void removePassenger(Elevator elevator, Passenger passenger) {
        elevator.getPassengerList().remove(passenger);
    }

    public static void removePassenger(Elevator elevator, int index) {
        elevator.getPassengerList().remove(index);
    }

    public static void removePassengers(Elevator elevator, List<Passenger> passengerList) {
        elevator.getPassengerList().removeAll(passengerList);
    }

}
