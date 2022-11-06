package com.gmail.mor.elevator.manager;

import com.gmail.mor.elevator.entiy.Building;
import com.gmail.mor.elevator.entiy.BuildingStatePrinter;

import java.io.File;

public class ApplicationManager {

    //Starts elevator.
    //Provides transportation of passengers between floors.
    //Starts frontend part of application (console output + file output)
    public static void startApplication(Building building){

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

}
