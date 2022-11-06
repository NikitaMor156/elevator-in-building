package com.gmail.mor.elevator.entiy;

import com.gmail.mor.elevator.constants.AppManager;
import com.gmail.mor.elevator.manager.ApplicationManager;
import com.gmail.mor.elevator.manager.BuildingManager;
import com.gmail.mor.elevator.manager.ElevatorManager;
import com.gmail.mor.elevator.manager.FloorManager;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Data
@Component("elevatorBean")
@NoArgsConstructor
public class Elevator {

    private int position = 0;
    private List<Passenger> passengerList = new ArrayList<>();
    private final int maxSize = AppManager.ELEVATOR_CAPACITY;
    private boolean isGoingUp = true;
    private List<Floor> floorList;

    public Elevator(List<Floor> floorList) {
        this.floorList = floorList;
    }

    //Can we take this passenger onboard?
    public boolean canTakePassenger(Passenger passenger) {
        if (passengerList.size() >= maxSize) {
            return false;
        }
        if (isGoingUp && passenger.getDestinationFloor() > position) {
            return true;
        }
        if (!isGoingUp && passenger.getDestinationFloor() < position) {
            return true;
        }
        return false;
    }

    public void move() {
        //If all passengers are on their places than elevator doesn't move
        if (BuildingManager.areAllPassengersOnTheirDestinationFloors(floorList) && this.isEmpty()) {
            return;
        }

        if (isGoingUp) {
            moveUp();
        } else {
            moveDown();
        }
    }

    private void moveUp() {
        position++;
    }

    private void moveDown() {
        position--;
    }

    //Returns true if elevator is called from above
    //(If the relevant call button pressed on the floor which is above the elevator.
    //That pressed button must have the same direction as elevator move direction, otherwise it will be ignored).
    private boolean isCalledFromAbove() {
        if (position >= AppManager.FLOOR_COUNT - 1) {
            return false;
        }
        List<Floor> floorsAbove = floorList.subList(position + 1, AppManager.FLOOR_COUNT);
        for (Floor fl : floorsAbove) {
            if (fl.hasPassengersToTake()) {
                return true;
            }
        }
        //Если кто-то из пассажиров лифта хочет наверх
        for (Passenger pas : passengerList) {
            if (pas.getDestinationFloor() > position) {
                return true;
            }
        }
        return false;
    }

    //Returns true if elevator is called from below
    //(If the relevant call button pressed on the floor which is below the elevator.
    //That pressed button must have the same direction as elevator move direction, otherwise it will be ignored).
    private boolean isCalledFromBelow() {
        if (position <= 0) {
            return false;
        }
        List<Floor> floorsBelow = floorList.subList(0, position);
        for (Floor fl : floorsBelow) {
            if (fl.hasPassengersToTake()) {
                return true;
            }
        }
        for (Passenger pas : passengerList) {
            if (pas.getDestinationFloor() < position) {
                return true;
            }
        }
        return false;
    }

    public void changeDirectionOfMoveIfItIsNecessary() {
        if (position == 0) {
            isGoingUp = true;
        }
        if (position == AppManager.FLOOR_COUNT - 1) {
            isGoingUp = false;
        }
        if (isGoingUp && !isCalledFromAbove()) {
            isGoingUp = false;
        }
        if (!isGoingUp && !isCalledFromBelow()) {
            isGoingUp = true;
        }
        dropAndPickUpPassengers();
    }

    public void dropAndPickUpPassengers() {
        dropOffPassengers();
        takePassengers();
    }

    private void dropOffPassengers() {
        List<Passenger> passengersToDrop = passengerList
                .stream()
                .filter(passenger -> passenger.getDestinationFloor() == position)
                .toList();
        FloorManager.addPassengers(floorList.get(position), passengersToDrop);
        ElevatorManager.removePassengers(this, passengersToDrop);
    }

    private void takePassengers() {
//        List<Passenger> floorPassengers = floorList.get(position).getPassengers();
//        for (int i = 0; i < floorPassengers.size(); i++) {
//            Passenger pas = floorPassengers.get(i);
//            if (canTakePassenger(pas)) {
//                passengerList.add(pas);
//                floorPassengers.set(i, null);
//            }
//        }
//        //delete null elements
//        floorPassengers.removeIf(Objects::isNull);
/////////////
        if (passengerList.size() == maxSize){
            return;
        }
        List<Passenger> passengersToTake = floorList.get(position).getPassengers()
                .stream()
                .filter(passenger -> {
                    if (isGoingUp && passenger.getDestinationFloor() > position) {
                        return true;
                    }
                    if (!isGoingUp && passenger.getDestinationFloor() < position) {
                        return true;
                    }
                    return false;
                })
                .toList();
        ElevatorManager.addPassengers(this, passengersToTake);
        FloorManager.removePassengers(floorList.get(position), passengersToTake);

    }

    public boolean isEmpty() {
        return passengerList.isEmpty();
    }

}
