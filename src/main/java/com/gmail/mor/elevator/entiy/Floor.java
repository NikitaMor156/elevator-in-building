package com.gmail.mor.elevator.entiy;

import com.gmail.mor.elevator.generator.EntityGenerator;
import lombok.Data;

import java.util.List;

@Data
public class Floor {
    //This floor number
    private int number;
    //Passengers on this floor
    private List<Passenger> passengers;

    //Constructor
    public Floor(int number) {
        this.number = number;
        int passengerCount = (int) (Math.random() * 10 + 1);
        this.passengers = EntityGenerator.generatePassengersList(passengerCount);
    }

    //Add passengers to this floor
    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    //Remove passenger from this floor
    public void removePassenger(Passenger passenger){
        passengers.remove(passenger);
    }

    //Remove passenger from floor by index
    public void removePassenger(int index){
        passengers.remove(index);
    }

    //Returns true if there are passengers on this floor which are not on their
    //destination floor at the moment.
    public boolean hasPassengersToTake(){
        for (Passenger pas : passengers){
            if (this.number != pas.getDestinationFloor()){
                return true;
            }
        }
        return false;
    }

}
