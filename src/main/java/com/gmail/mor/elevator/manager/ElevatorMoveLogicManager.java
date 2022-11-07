package com.gmail.mor.elevator.manager;

import com.gmail.mor.elevator.constants.Conf;
import com.gmail.mor.elevator.entiy.Elevator;
import com.gmail.mor.elevator.entiy.Floor;
import com.gmail.mor.elevator.entiy.Passenger;

import java.util.List;

public class ElevatorMoveLogicManager {
    private Elevator elevator;

    public ElevatorMoveLogicManager(Elevator elevator) {
        this.elevator = elevator;
    }

    public void workOneStep() {
        ElevatorPassengerManager.dropOffPassengers(elevator);
        ElevatorPassengerManager.takePassengers(elevator);
        changeDirectionOfMoveIfItIsNecessary();
        move();
    }

    public void move() {
        //If all passengers are on their places than elevator doesn't move
        if (BuildingManager.areAllPassengersOnTheirDestinationFloors(elevator.getFloorList()) &&
                ElevatorPassengerManager.isElevatorEmpty(elevator)) {
            return;
        }

        if (elevator.isGoingUp()) {
            moveUp();
        } else {
            moveDown();
        }
    }

    private void moveUp() {
        elevator.setPosition(elevator.getPosition() + 1);
    }

    private void moveDown() {
        elevator.setPosition(elevator.getPosition() - 1);
    }

    //Returns true if elevator is called from above
    //(If the relevant call button pressed on the floor which is above the elevator.
    //That pressed button must have the same direction as elevator move direction, otherwise it will be ignored).
    private boolean isCalledFromAbove() {
        if (elevator.getPosition() >= Conf.FLOOR_COUNT - 1) {
            return false;
        }
        List<Floor> floorsAbove = elevator.getFloorList().subList(elevator.getPosition() + 1, Conf.FLOOR_COUNT);
        for (Floor fl : floorsAbove) {
            if (fl.hasPassengersToTake()) {
                return true;
            }
        }
        //Если кто-то из пассажиров лифта хочет наверх
        for (Passenger pas : elevator.getPassengers()) {
            if (pas.getDestinationFloor() > elevator.getPosition()) {
                return true;
            }
        }
        return false;
    }

    //Returns true if elevator is called from below
    //(If the relevant call button pressed on the floor which is below the elevator.
    //That pressed button must have the same direction as elevator move direction, otherwise it will be ignored).
    private boolean isCalledFromBelow() {
        if (elevator.getPosition() <= 0) {
            return false;
        }
        List<Floor> floorsBelow = elevator.getFloorList().subList(0, elevator.getPosition());
        for (Floor fl : floorsBelow) {
            if (fl.hasPassengersToTake()) {
                return true;
            }
        }
        for (Passenger pas : elevator.getPassengers()) {
            if (pas.getDestinationFloor() < elevator.getPosition()) {
                return true;
            }
        }
        return false;
    }

    public void changeDirectionOfMoveIfItIsNecessary() {
        if (elevator.getPosition() == 0) {
            elevator.setGoingUp(true);
        }
        if (elevator.getPosition() == Conf.FLOOR_COUNT - 1) {
            elevator.setGoingUp(false);
        }
        if (elevator.isGoingUp() && !isCalledFromAbove()) {
            elevator.setGoingUp(false);
        }
        if (!elevator.isGoingUp() && !isCalledFromBelow()) {
            elevator.setGoingUp(true);
        }
        ElevatorPassengerManager.dropOffPassengers(elevator);
        ElevatorPassengerManager.takePassengers(elevator);
    }


}
