package com.gmail.mor.elevator;

import com.gmail.mor.elevator.constants.AppManager;
import com.gmail.mor.elevator.entiy.Building;
import com.gmail.mor.elevator.entiy.Elevator;
import com.gmail.mor.elevator.entiy.FrontEndMaker;
import com.gmail.mor.elevator.entiy.Passenger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElevatorApplication {

    public static void main(String[] args) {
        System.out.println(AppManager.FLOOR_COUNT);

        Building building = new Building(new Elevator());
        System.out.println(building);
        FrontEndMaker.printBuilding(building);
    }

}
