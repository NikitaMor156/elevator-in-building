package com.gmail.mor.elevator.entiy;

import com.gmail.mor.elevator.constants.AppManager;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Elevator {

    private int position;
    private List<Passenger> passengerList;
    private int maxSize;
    private boolean isGoingUp = true;

    public Elevator() {
        passengerList = new ArrayList<>();
        position = 0;
    }

    //Can we take this passenger onboard?
    public boolean canTakePassenger(Passenger passenger) {
        if (passengerList.size() == maxSize) {
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
        changeDirectionOfMoveIfItIsNecessary();
        if (isGoingUp) {
            position++;
        } else {
            position--;
        }
        for (Passenger p : passengerList){
            p.setPosition(position);
        }
    }

    private void changeDirectionOfMoveIfItIsNecessary() {
        //Elevator is on 1-st floor
        if (position == 0) {
            isGoingUp = true;
        }
        if (position == AppManager.FLOOR_COUNT - 1) {
            isGoingUp = false;
        }
    }

    private void dropOffPassengers(){
        for (Passenger p : passengerList){
            if (p.getDestinationFloor() == p.getPosition()){

            }
        }
    }


}
