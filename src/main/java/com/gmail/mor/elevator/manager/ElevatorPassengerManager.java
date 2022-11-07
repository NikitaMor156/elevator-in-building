package com.gmail.mor.elevator.manager;

import com.gmail.mor.elevator.entiy.Elevator;
import com.gmail.mor.elevator.entiy.Passenger;

import java.util.ArrayList;
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

    public static void dropOffPassengers(Elevator elevator) {
        List<Passenger> passengersToDrop = elevator.getPassengers()
                .stream()
                .filter(passenger -> passenger.getDestinationFloor() == elevator.getPosition())
                .toList();
        FloorManager.addPassengers(elevator.getFloorList().get(elevator.getPosition()), passengersToDrop);
        ElevatorPassengerManager.removePassengers(elevator, passengersToDrop);
    }

    public static void takePassengers(Elevator elevator) {
        if (elevator.getPassengers().size() == elevator.getCapacity()) {
            return;
        }
        int freeSlotsCount = elevator.getCapacity() - elevator.getPassengers().size();
        List<Passenger> passengersToTake = new ArrayList<>(elevator.getFloorList().get(elevator.getPosition()).getPassengers())
                .stream()
                .filter(passenger -> {
                    if (elevator.isGoingUp() && passenger.getDestinationFloor() > elevator.getPosition()) {
                        return true;
                    }
                    if (!elevator.isGoingUp() && passenger.getDestinationFloor() < elevator.getPosition()) {
                        return true;
                    }
                    return false;
                })
                .limit(freeSlotsCount)
                .toList();
        ElevatorPassengerManager.addPassengers(elevator, passengersToTake);
        FloorManager.removePassengers(elevator.getFloorList().get(elevator.getPosition()), passengersToTake);

    }

}
