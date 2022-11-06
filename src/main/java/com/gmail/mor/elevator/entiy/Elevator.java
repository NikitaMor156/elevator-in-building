package com.gmail.mor.elevator.entiy;

import com.gmail.mor.elevator.constants.AppManager;
import com.gmail.mor.elevator.manager.BuildingManager;
import com.gmail.mor.elevator.manager.ElevatorLogicManager;
import com.gmail.mor.elevator.manager.ElevatorPassengerManager;
import com.gmail.mor.elevator.manager.FloorManager;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component("elevatorBean")
@NoArgsConstructor
public class Elevator {

    private int position = 0;
    private boolean isGoingUp = true;
    private List<Passenger> passengerList = new ArrayList<>();
    private final int maxSize = AppManager.ELEVATOR_CAPACITY;
    private List<Floor> floorList;
    private ElevatorLogicManager logic;

    public Elevator(List<Floor> floorList) {
        this.floorList = floorList;
        this.logic = new ElevatorLogicManager(this);
    }

//    //Can we take this passenger onboard?
//    public boolean canTakePassenger(Passenger passenger) {
//        if (passengerList.size() >= maxSize) {
//            return false;
//        }
//        if (isGoingUp && passenger.getDestinationFloor() > position) {
//            return true;
//        }
//        if (!isGoingUp && passenger.getDestinationFloor() < position) {
//            return true;
//        }
//        return false;
//    }

//    public void move() {
//        //If all passengers are on their places than elevator doesn't move
//        if (BuildingManager.areAllPassengersOnTheirDestinationFloors(floorList) && this.isEmpty()) {
//            return;
//        }
//
//        if (isGoingUp) {
//            moveUp();
//        } else {
//            moveDown();
//        }
//    }
//
//    private void moveUp() {
//        position++;
//    }
//
//    private void moveDown() {
//        position--;
//    }
//
//    //Returns true if elevator is called from above
//    //(If the relevant call button pressed on the floor which is above the elevator.
//    //That pressed button must have the same direction as elevator move direction, otherwise it will be ignored).
//    private boolean isCalledFromAbove() {
//        if (position >= AppManager.FLOOR_COUNT - 1) {
//            return false;
//        }
//        List<Floor> floorsAbove = floorList.subList(position + 1, AppManager.FLOOR_COUNT);
//        for (Floor fl : floorsAbove) {
//            if (fl.hasPassengersToTake()) {
//                return true;
//            }
//        }
//        //Если кто-то из пассажиров лифта хочет наверх
//        for (Passenger pas : passengerList) {
//            if (pas.getDestinationFloor() > position) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    //Returns true if elevator is called from below
//    //(If the relevant call button pressed on the floor which is below the elevator.
//    //That pressed button must have the same direction as elevator move direction, otherwise it will be ignored).
//    private boolean isCalledFromBelow() {
//        if (position <= 0) {
//            return false;
//        }
//        List<Floor> floorsBelow = floorList.subList(0, position);
//        for (Floor fl : floorsBelow) {
//            if (fl.hasPassengersToTake()) {
//                return true;
//            }
//        }
//        for (Passenger pas : passengerList) {
//            if (pas.getDestinationFloor() < position) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public void changeDirectionOfMoveIfItIsNecessary() {
//        if (position == 0) {
//            isGoingUp = true;
//        }
//        if (position == AppManager.FLOOR_COUNT - 1) {
//            isGoingUp = false;
//        }
//        if (isGoingUp && !isCalledFromAbove()) {
//            isGoingUp = false;
//        }
//        if (!isGoingUp && !isCalledFromBelow()) {
//            isGoingUp = true;
//        }
//        dropAndPickUpPassengers();
//    }
//
//    public void dropAndPickUpPassengers() {
//        dropOffPassengers();
//        takePassengers();
//    }
//
//    private void dropOffPassengers() {
//        List<Passenger> passengersToDrop = passengerList
//                .stream()
//                .filter(passenger -> passenger.getDestinationFloor() == position)
//                .toList();
//        FloorManager.addPassengers(floorList.get(position), passengersToDrop);
//        ElevatorPassengerManager.removePassengers(this, passengersToDrop);
//    }
//
//    private void takePassengers() {
//        if (passengerList.size() == maxSize){
//            return;
//        }
//        List<Passenger> passengersToTake = floorList.get(position).getPassengers()
//                .stream()
//                .filter(passenger -> {
//                    if (isGoingUp && passenger.getDestinationFloor() > position) {
//                        return true;
//                    }
//                    if (!isGoingUp && passenger.getDestinationFloor() < position) {
//                        return true;
//                    }
//                    return false;
//                })
//                .toList();
//        ElevatorPassengerManager.addPassengers(this, passengersToTake);
//        FloorManager.removePassengers(floorList.get(position), passengersToTake);
//
//    }
//
//    public boolean isEmpty() {
//        return passengerList.isEmpty();
//    }

}
