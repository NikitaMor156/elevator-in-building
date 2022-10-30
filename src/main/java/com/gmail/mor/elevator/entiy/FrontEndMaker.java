package com.gmail.mor.elevator.entiy;

import lombok.Data;

import java.util.List;
@Data
public class FrontEndMaker {

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
                System.out.print("p(" + p.getDestinationFloor() + ") ");
            }
            System.out.println();
        }
    }

}
