package com.gmail.mor.elevator.entiy;

import com.gmail.mor.elevator.EntityGenerator;
import com.gmail.mor.elevator.constants.AppManager;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

//Building is class which contains list of Floor objects where passengers are and Elevator
//object which provides transportation of passengers after start() method of Building class is executed
@Data
@Component("buildingBean")
public class Building {

    //List of floors in this building
    private List<Floor> floorList;

    //Elevator of this building
    @Autowired
    private Elevator elevator;

    public Building() {
        //Generate random count of floors (floor list).
        //This floors will be automatically filled with random count of passengers.
        floorList = EntityGenerator.generateFloorList(AppManager.FLOOR_COUNT);
    }

    //Returns true if all passengers floor position is equal to destination.
    //That means that all passengers which are in the building used to get to their
    //destination floors and elevator is empty.
    //Otherwise, returns false.
    public boolean areAllPassengersOnTheirDestinationFloors() {
        boolean isDone = true;
        List<Passenger> allPassengers = getAllPassengers();
        for (Passenger p : allPassengers) {
            if (p.getDestinationFloor() != p.getPosition()) {
                isDone = false;
                break;
            }
        }
        return isDone && elevator.getPassengerList().isEmpty();
    }

    //Returns list of all passengers which are present in the building and elevator.
    public List<Passenger> getAllPassengers() {
        List<Passenger> result = new ArrayList<>();
        for (Floor f : floorList) {
            result.addAll(f.getPassengerList());
        }
        result.addAll(elevator.getPassengerList());
        return result;
    }

    //Returns list of all passengers from current floor
    public List<Passenger> getFloorPassengers(int index) {
        return floorList.get(index).getPassengerList();
    }

    //Returns Floor object by index
    public Floor getFloor(int index) {
        return floorList.get(index);
    }

    //Returns list of Floor objects from fromIndex to toIndex (inclusive)
    public List<Floor> getFloors(int fromIndex, int toIndex) {
        List<Floor> result = new ArrayList<>();
        for (int i = fromIndex; i <= toIndex; i++) {
            result.add(floorList.get(i));
        }
        return result;
    }

    //Starts elevator.
    //Provides transportation of passengers between floors.
    //Starts frontend part of application (console output + file output)
    public void start() {
        while (!areAllPassengersOnTheirDestinationFloors()) {
            FrontEndMaker.printBuildingStateToConsole(this);
            FrontEndMaker.printBuildingStateToFile(this);

            elevator.dropAndPickUpPassengers();
            elevator.changeDirectionOfMoveIfItIsNecessary();
            elevator.move();
        }
        FrontEndMaker.printBuildingStateToConsole(this);
        FrontEndMaker.printBuildingStateToFile(this);

        System.out.println(">>> ATTENTION! <<<");
        System.out.println("The output can be found also in txt file on path:"
                + new File(FrontEndMaker.OUTPUT_FILE_NAME).getAbsolutePath());

    }

    @Override
    public String toString() {
        return "Building{" +
                "floorList=" + floorList +
                ", elevator=" + "AVOID OF DEATH LOOP CAUSED BY CIRCULAR DEPENDENCY!" +
                '}';
    }
}
