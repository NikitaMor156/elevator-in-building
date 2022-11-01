package com.gmail.mor.elevator.entiy;

import com.gmail.mor.elevator.EntityGenerator;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Floor {
    //This floor number
    private int number;
    private List<Passenger> passengerList;
    private boolean isUppButtonPressed = false;
    private boolean isDownButtonPressed = false;

    public Floor(int number) {
        this.number = number;
        int passengerCount = (int) (Math.random() * 10 + 1);
        this.passengerList = EntityGenerator.generatePassengersList(passengerCount, number);
        updatePressedButtons();
    }

    public void putPassenger(Passenger passenger) {
        passengerList.add(passenger);
    }

    public void updatePressedButtons() {
        for (Passenger passenger : passengerList) {
            if (passenger.getDestinationFloor() > number) {
                isUppButtonPressed = true;
            }
            if (passenger.getDestinationFloor() < number) {
                isDownButtonPressed = true;
            }
        }
    }
}
