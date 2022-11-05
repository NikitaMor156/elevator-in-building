package com.gmail.mor.elevator.entiy;

import com.gmail.mor.elevator.EntityGenerator;
import lombok.Data;

import java.util.List;

@Data
public class Floor {
    //This floor number
    private int number;
    private List<Passenger> passengers;

    public Floor(int number) {
        this.number = number;
        int passengerCount = (int) (Math.random() * 10 + 1);
        this.passengers = EntityGenerator.generatePassengersList(passengerCount, number);
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    public void removePassenger(Passenger passenger){
        passengers.remove(passenger);
    }

    public void removePassenger(int index){
        passengers.remove(index);
    }

    public boolean hasPassengersToTake(){
        for (Passenger pas : passengers){
            if (pas.getPosition() != pas.getDestinationFloor()){
                return true;
            }
        }
        return false;
    }

}
