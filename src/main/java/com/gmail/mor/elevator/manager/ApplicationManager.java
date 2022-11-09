package com.gmail.mor.elevator.manager;

import com.gmail.mor.elevator.entiy.*;
import com.gmail.mor.elevator.file.FileCleaner;
import com.gmail.mor.elevator.statewriter.BuildingStateWriter;

import java.io.File;

public class ApplicationManager {

    //Starts application.
    //Provides transportation of passengers between floors in building.
    //Building, it's floors and passengers on these floors generate randomly (with random quantity)
    //This method uses frontend part of application (console output + file output)
    public static void startApplication() {
        //Make log file empty every time before program run.
        FileCleaner.clearFile(BuildingStateWriter.DEFAULT_OUTPUT_FILE_NAME);

        //Crate new building.
        Building building = new Building();

        //Loop until all passengers will be on their destination floor and elevator will be empty
        while (!ElevatorPassengerManager.isElevatorEmpty(building.getElevator()) ||
                !BuildingPassengerManager.areAllPassengersOnTheirDestinationFloors(building)) {

            //Print state of building and all objects inside it to console
            BuildingStateWriter.printBuildingStateToConsole(building);
            //Print state of building and all objects inside it to output file (default value - "output.txt")
            BuildingStateWriter.printBuildingStateToDefaultFile(building);

            //Elevator makes one "step". That means that the elevator performs one set
            //of operations that end up moving the elevator 1 floor up or down.
            building.getElevator().getLogic().workOneStep();
        }

        //Log of final state of building before program end

        //Print state of building and all objects inside it to console
        BuildingStateWriter.printBuildingStateToConsole(building);
        //Print state of building and all objects inside it to output file (default value - "output.txt")
        BuildingStateWriter.printBuildingStateToDefaultFile(building);

        //Print legend to console
        BuildingStateWriter.printLegendToConsole();
        //Print legend to default output file
        BuildingStateWriter.printLegendToDefaultFile();

        System.out.println(">>> ATTENTION! <<<");
        System.out.println("The output can be found also in txt file on path: "
                + new File(BuildingStateWriter.DEFAULT_OUTPUT_FILE_NAME).getAbsolutePath());
        System.out.println("Done.");
    }


}
