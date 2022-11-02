package com.gmail.mor.elevator.entiy;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Data
@Component
public class FrontEndMaker {
    private static final String OUTPUT_FILE_NAME = "log.txt";

    //Make log file empty before every program run
    @PostConstruct
    public void init() {
        clearFile(OUTPUT_FILE_NAME);
    }

    public static void printBuilding(Building building) {
        System.out.print(getProgramOutputString(building));
    }

    public static void writeFrontEndToLogFile(Building building) {
        try {
            Files.write(Paths.get(OUTPUT_FILE_NAME),
                    getProgramOutputString(building).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    private void clearFile(String fileName) {
        try (var writer = new PrintWriter(fileName)) {
            writer.print("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
