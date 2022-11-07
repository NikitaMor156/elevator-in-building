package com.gmail.mor.elevator;

import com.gmail.mor.elevator.manager.ApplicationManager;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElevatorApplication {

    public static void main(String[] args) {

        ApplicationManager.startApplication();

    }

}
