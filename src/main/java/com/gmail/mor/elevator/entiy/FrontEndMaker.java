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

    @PostConstruct
    public void init(){
        clearFile("log.txt");
    }

    public static void printBuilding(Building building) {
        List<Floor> floorList = building.getFloorList();
        //for (int i = 0; i < floorList.size(); i++) {
        for (int i = floorList.size() - 1; i >= 0; i--) {
            if (building.getElevator().getPosition() == i) {
                if (building.getElevator().isGoingUp()) {
                    System.out.print("^");
                } else {
                    System.out.print("-");
                }
            } else {
                System.out.print(" ");
            }
            System.out.print("|" + (i + 1) + "-st fl.| ");
            for (Passenger p : floorList.get(i).getPassengerList()) {
                System.out.print("p(" + (p.getDestinationFloor() + 1) + ") ");
            }
            System.out.println();
        }
        System.out.println("elev.: " + building.getElevator().getPassengerList());
        System.out.println("----------------------------------------------------");
    }

    //Writo to log file
    public static void writeFrontEndToLogFile(Building building) {
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
            sb.append("|").append(i + 1).append("-st fl.| ");
            for (Passenger p : floorList.get(i).getPassengerList()) {
                sb.append("p(").append(p.getDestinationFloor() + 1).append(") ");
            }
            sb.append(System.lineSeparator());
        }
        sb.append("elev.: ").append(building.getElevator().getPassengerList()).append(System.lineSeparator());
        sb.append("----------------------------------------------------");
        sb.append(System.lineSeparator());
        try {
            Files.write(Paths.get("log.txt"), sb.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearFile(String fileName) {
        try (var writer =new PrintWriter(fileName)){
            writer.print("");
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }

}
