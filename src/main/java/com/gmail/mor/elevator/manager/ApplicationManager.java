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
        while (!building.getElevator().isEmpty() || !BuildingManager.areAllPassengersOnTheirDestinationFloors(building)) {
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


}
