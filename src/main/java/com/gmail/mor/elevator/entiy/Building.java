package com.gmail.mor.elevator.entiy;

import com.gmail.mor.elevator.generator.EntityGenerator;
import com.gmail.mor.elevator.constants.AppManager;
import lombok.Data;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//Building is class which contains list of Floor objects where passengers are and Elevator
//object which provides transportation of passengers after start() method of Building class is executed
@Data
@Component("buildingBean")
public class Building {

    //List of floors in this building
    private List<Floor> floors;

    //Elevator of this building
   // @Autowired
    private Elevator elevator;

    public Building() {
        //Generate random count of floors (floor list).
        //This floors will be automatically filled with random count of passengers.
        floors = EntityGenerator.generateFloorList(AppManager.FLOOR_COUNT);
        elevator = new Elevator(floors);
    }

    //Returns true if all passengers floor position is equal to destination.
    //That means that all passengers which are in the building used to get to their
    //destination floors and elevator is empty.
    //Otherwise, returns false.
    public boolean areAllPassengersOnTheirDestinationFloors() {
        if (!elevator.isEmpty()){
            return false;
        }
        for (Floor floor : floors){
            for (Passenger p : floor.getPassengers()){
                if (p.getDestinationFloor() != floor.getNumber()){
                    return false;
                }
            }
        }
        return true;
    }

    //Returns list of all passengers which are present in the building and elevator.
    public List<Passenger> getAllPassengers() {
        List<Passenger> result = new ArrayList<>();
        for (Floor f : floors) {
            result.addAll(f.getPassengers());
        }
        result.addAll(elevator.getPassengerList());
        return result;
    }

    //Returns list of all passengers from current floor
    public List<Passenger> getFloorPassengers(int index) {
        return floors.get(index).getPassengers();
    }

    //Returns Floor object by index
    public Floor getFloor(int index) {
        return floors.get(index);
    }

    //Returns list of Floor objects from fromIndex to toIndex (inclusive)
    public List<Floor> getFloors(int fromIndex, int toIndex) {
        List<Floor> result = new ArrayList<>();
        for (int i = fromIndex; i <= toIndex; i++) {
            result.add(floors.get(i));
        }
        return result;
    }

    @Override
    public String toString() {
        return "Building{" +
                "floorList=" + floors +
                ", elevator=" + "AVOID OF DEATH LOOP CAUSED BY CIRCULAR DEPENDENCY!" +
                '}';
    }
}
