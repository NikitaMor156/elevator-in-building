package com.gmail.mor.elevator.manager;

import com.gmail.mor.elevator.entiy.Elevator;
import com.gmail.mor.elevator.entiy.Passenger;

import java.util.List;

public class ElevatorPassengerManager {

    public static void addPassenger(Elevator elevator, Passenger passenger) {
        elevator.getPassengers().add(passenger);
    }

    public static void addPassengers(Elevator elevator, List<Passenger> passengerList) {
        elevator.getPassengers().addAll(passengerList);
    }

    public static void removePassenger(Elevator elevator, Passenger passenger) {
        elevator.getPassengers().remove(passenger);
    }

    public static void removePassenger(Elevator elevator, int index) {
        elevator.getPassengers().remove(index);
    }

    public static void removePassengers(Elevator elevator, List<Passenger> passengerList) {
        elevator.getPassengers().removeAll(passengerList);
    }

    public static boolean isElevatorEmpty(Elevator elevator) {
        return elevator.getPassengers().isEmpty();
    }

}
