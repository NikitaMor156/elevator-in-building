package com.gmail.mor.elevator;

import com.gmail.mor.elevator.entiy.Building;
import com.gmail.mor.elevator.entiy.Elevator;
import com.gmail.mor.elevator.entiy.BuildingStatePrinter;
import com.gmail.mor.elevator.manager.ApplicationManager;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ElevatorApplication {

    public static void main(String[] args) {

        ApplicationManager.startApplication();

    }

}
