package com.gmail.mor.elevator.printer;

import com.gmail.mor.elevator.constants.Conf;
import com.gmail.mor.elevator.entiy.Building;
import com.gmail.mor.elevator.entiy.Floor;
import com.gmail.mor.elevator.entiy.Passenger;
import com.gmail.mor.elevator.file.FileCleaner;
import com.gmail.mor.elevator.file.FileWriter;
import com.gmail.mor.elevator.manager.ElevatorPassengerManager;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;

//Class represents frontend part of application.
//This is a sequence of states of the building,
//where each new slide is the movement of the elevator to 1 floor. Every movement is called step.
//Its public methods are used to print "frontend output" to .txt file or to console.
@Data
@Component
@NoArgsConstructor
public class BuildingStatePrinter {

    //Name of file for "frontend output".
    public static final String DEFAULT_OUTPUT_FILE_NAME = Conf.OUTPUT_FILE_NAME;

    //Writes the result of getProgramOutputString(Building) method to console
    public static void printBuildingStateToConsole(Building building) {
        System.out.print(getProgramOutputString(building));
    }

    //Writes the result of getProgramOutputString(Building) method
    //to the file (OUTPUT_FILE_NAME variable of this class is file name)
    public static void printBuildingStateToFile(Building building) {
        FileWriter.appendStringToFile(getProgramOutputString(building), DEFAULT_OUTPUT_FILE_NAME);
    }

    //TODO
    public static void printBuildingStateToFile(Building building, File file) {
        FileWriter.appendStringToFile(getProgramOutputString(building), file.getAbsolutePath());
    }

    //This method generates String which represents the state of the building and it's elevator
    //at the moment
    private static String getProgramOutputString(Building building) {
        List<Floor> floorList = building.getFloors();
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
            for (Passenger p : floorList.get(i).getPassengers()) {
                sb.append("p(")
                        .append(p.getDestinationFloor() + 1)
                        .append(") ");
            }
            sb.append(System.lineSeparator());
        }
        sb.append("elevator passengers: [");
        for (Passenger pas : building.getElevator().getPassengers()) {
            sb.append("p(")
                    .append(pas.getDestinationFloor() + 1)
                    .append("), ");
        }
        if (!ElevatorPassengerManager.isElevatorEmpty(building.getElevator())) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]")
                .append(System.lineSeparator())
                .append("----------------------------------------------------")
                .append(System.lineSeparator());
        return sb.toString();
    }
}
