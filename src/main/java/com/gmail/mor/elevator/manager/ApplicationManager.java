package com.gmail.mor.elevator.manager;

import com.gmail.mor.elevator.entiy.*;

import java.io.File;
import java.util.List;

public class ApplicationManager {

    //Starts elevator.
    //Provides transportation of passengers between floors.
    //Starts frontend part of application (console output + file output)
    public static void startApplication(){
        Building building = new Building();

        //TODO isDone method
        while (!building.areAllPassengersOnTheirDestinationFloors()) {
            BuildingStatePrinter.printBuildingStateToConsole(building);
            BuildingStatePrinter.printBuildingStateToFile(building);

            building.getElevator().dropAndPickUpPassengers();
            building.getElevator().changeDirectionOfMoveIfItIsNecessary();
            building.getElevator().move();
        }
        BuildingStatePrinter.printBuildingStateToConsole(building);
        BuildingStatePrinter.printBuildingStateToFile(building);

        System.out.println(">>> ATTENTION! <<<");
        System.out.println("The output can be found also in txt file on path:"
                + new File(BuildingStatePrinter.OUTPUT_FILE_NAME).getAbsolutePath());
        System.out.println("Done.");
    }

    //Returns true if all passengers floor position is equal to destination.
    //That means that all passengers which are in the building used to get to their
    //destination floors and elevator is empty.
    //Otherwise, returns false.
    public static boolean areAllPassengersOnTheirDestinationFloors(List<Floor> floorList) {
        for (Floor floor : floorList){
            for (Passenger p : floor.getPassengers()){
                if (p.getDestinationFloor() != floor.getNumber()){
                    return false;
                }
            }
        }
        return true;
    }

}
