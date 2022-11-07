package com.gmail.mor.elevator.manager;

import com.gmail.mor.elevator.entiy.*;
import com.gmail.mor.elevator.file.FileCleaner;
import com.gmail.mor.elevator.printer.BuildingStatePrinter;

import java.io.File;

public class ApplicationManager {

    //Starts elevator.
    //Provides transportation of passengers between floors.
    //Starts frontend part of application (console output + file output)
    public static void startApplication() {
        //Make log file empty every time before program run.
        FileCleaner.clearFile(BuildingStatePrinter.DEFAULT_OUTPUT_FILE_NAME);

        Building building = new Building();

        while (!ElevatorPassengerManager.isElevatorEmpty(building.getElevator()) ||
                !BuildingManager.areAllPassengersOnTheirDestinationFloors(building)) {

            BuildingStatePrinter.printBuildingStateToConsole(building);
            BuildingStatePrinter.printBuildingStateToFile(building);

            building.getElevator().getLogic().workOneStep();
        }
        BuildingStatePrinter.printBuildingStateToConsole(building);
        BuildingStatePrinter.printBuildingStateToFile(building);

        System.out.println(">>> ATTENTION! <<<");
        System.out.println("The output can be found also in txt file on path: "
                + new File(BuildingStatePrinter.DEFAULT_OUTPUT_FILE_NAME).getAbsolutePath());
        System.out.println("Done.");
    }


}
