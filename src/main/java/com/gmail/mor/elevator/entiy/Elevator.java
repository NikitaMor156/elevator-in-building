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
        if (changeDirectionOfMoveIfItIsNecessary()){
            dropOffPassengers();
            takePassengers();
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

    private boolean changeDirectionOfMoveIfItIsNecessary() {
        //Elevator is on 1-st floor
        if (position == 0) {
            isGoingUp = true;
            return true;
        }
        //Elevator is on last floor
        if (position == AppManager.FLOOR_COUNT - 1) {
            isGoingUp = false;
            return true;
        }
        return false;
    }

    public void dropOffPassengers() {
        for (int i = 0; i < passengerList.size(); i++) {
            Passenger pas = passengerList.get(i);
            if (pas.getDestinationFloor() == pas.getPosition()) {
                building.getFloorList().get(position).getPassengerList().add(pas);
                this.passengerList.remove(pas);
            }
        }

    }

    //NOT WORKING!!!
    public void takePassengers() {
        List<Passenger> floorPassengers = building.getFloorList().get(position).getPassengerList();
        for (int i = 0; i < floorPassengers.size(); i++) {
            Passenger pas = floorPassengers.get(i);
            if (canTakePassenger(pas)) {
                passengerList.add(pas);
                floorPassengers.set(i,null);
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
