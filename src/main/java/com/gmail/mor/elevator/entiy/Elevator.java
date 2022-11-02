package com.gmail.mor.elevator.entiy;

import com.gmail.mor.elevator.constants.AppManager;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Component("elevatorBean")
public class Elevator {

    //Баг с position!!!
    private int position;
    private List<Passenger> passengerList;
    private final int maxSize = AppManager.ELEVATOR_CAPACITY;
    private boolean isGoingUp = true;
    @Autowired
    Building building;

    public Elevator() {
        passengerList = new ArrayList<>();
        position = 0;
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
        if (building.areAllPassengersOnTheirDestinationFloors()) {
            return;
        }

        if (isGoingUp) {
            position++;
        } else {
            position--;
        }
        for (Passenger p : passengerList) {
            p.setPosition(position);
        }
    }

    //TODO BUG
    //Returns true if elevator is called from above
    //(If the relevant call button pressed on the floor which is above the elevator.
    //That pressed button must have the same direction as elevator move direction, otherwise it will be ignored).
    private boolean isCalledFromAbove() {
        if (position >= AppManager.FLOOR_COUNT - 1) {
            return false;
        }
        List<Floor> floorsAbove = building.getFloors(position + 1, AppManager.FLOOR_COUNT - 1);
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
        List<Floor> floorsBelow = building.getFloors(0, position - 1);
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
        for (int i = 0; i < passengerList.size(); i++) {
            Passenger pas = passengerList.get(i);
            if (pas.getDestinationFloor() == pas.getPosition()) {
                building.getFloor(position).addPassenger(pas);
                this.passengerList.set(i,null);
            }
        }
        passengerList.removeIf(Objects::isNull);
    }

    private void takePassengers() {
        List<Passenger> floorPassengers = building.getFloor(position).getPassengerList();
        for (int i = 0; i < floorPassengers.size(); i++) {
            Passenger pas = floorPassengers.get(i);
            if (canTakePassenger(pas)) {
                passengerList.add(pas);
                floorPassengers.set(i, null);
            }
        }
        //delete null elements
        floorPassengers.removeIf(Objects::isNull);
    }

    @Override
    public String toString() {
        return "Elevator{" +
                "position=" + position +
                ", passengerList=" + passengerList +
                ", maxSize=" + maxSize +
                ", isGoingUp=" + isGoingUp +
                ", building=" + "AVOID OF DEATH LOOP CAUSED BY CIRCULAR DEPENDENCY!" +
                '}';
    }
}
