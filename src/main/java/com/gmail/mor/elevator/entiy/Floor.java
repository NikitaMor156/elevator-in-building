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

    public Floor(int number) {
        this.number = number;
        int passengerCount = (int) (Math.random() * 10 + 1);
        this.passengerList = EntityGenerator.generatePassengersList(passengerCount, number);
    }

    public void addPassenger(Passenger passenger) {
        passengerList.add(passenger);
    }

    public void removePassenger(Passenger passenger){
        passengerList.remove(passenger);
    }

    public void removePassenger(int index){
        passengerList.remove(index);
    }

    public boolean hasPassengersToTake(){
        for (Passenger pas : passengerList){
            if (pas.getPosition() != pas.getDestinationFloor()){
                return true;
            }
        }
        return false;
    }

}
