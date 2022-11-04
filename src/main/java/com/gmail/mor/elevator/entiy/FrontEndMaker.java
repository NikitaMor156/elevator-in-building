package com.gmail.mor.elevator.entiy;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

//Class represents frontend part of application.
//This is a sequence of states of the building,
//where each new slide is the movement of the elevator to 1 floor. Every movement is called step.
//Its public methods are used to print "frontend output" to .txt file or to console.
@Data
@Component
@NoArgsConstructor
public class FrontEndMaker {

    //Name of file for "frontend output".
    public static final String OUTPUT_FILE_NAME = "log.txt";

    //Make log file empty every time before program run.
    @PostConstruct
    public void init() {
        clearFile(OUTPUT_FILE_NAME);
    }

    //Writes the result of getProgramOutputString(Building) method to console
    public static void printBuildingStateToConsole(Building building) {
        System.out.print(getProgramOutputString(building));
    }

    //Writes the result of getProgramOutputString(Building) method
    //to the file (OUTPUT_FILE_NAME variable of this class is file name)
    public static void printBuildingStateToFile(Building building) {
        try {
            Files.write(Paths.get(OUTPUT_FILE_NAME),
                    getProgramOutputString(building).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //This method generates String which represents the state of all objects inside the building
    //at the moment
    public static String getProgramOutputString(Building building) {
        List<Floor> floorList = building.getFloorList();
        StringBuilder sb = new StringBuilder();
        for (int i = floorList.size() - 1; i >= 0; i--) {
            if (building.getElevator().getPosition() == i) {
                if (building.getElevator().isGoingUp()) {
                    sb.append("^");
                } else {
                    sb.append("-");
                }
            } else {
                sb.append(" ");
            }
            sb.append("|")
                    .append(i + 1)
                    .append("-st fl.| ");
            for (Passenger p : floorList.get(i).getPassengerList()) {
                sb.append("p(")
                        .append(p.getDestinationFloor() + 1)
                        .append(") ");
            }
            sb.append(System.lineSeparator());
        }
        sb.append("elevator passengers: [");
        //.append(building.getElevator().getPassengerList())
        for (Passenger pas : building.getElevator().getPassengerList()) {
            sb.append("p(")
                    .append(pas.getDestinationFloor())
                    .append("), ");
        }
        if (!building.getElevator().isEmpty()) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]")
                .append(System.lineSeparator())
                .append("----------------------------------------------------")
                .append(System.lineSeparator());
        return sb.toString();
    }

    //This method is used to clear file before (to delete old output from previous run)
    private void clearFile(String fileName) {
        try (var writer = new PrintWriter(fileName)) {
            writer.print("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
