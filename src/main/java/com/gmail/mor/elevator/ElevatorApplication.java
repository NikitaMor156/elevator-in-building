package com.gmail.mor.elevator;

import com.gmail.mor.elevator.entiy.Building;
import com.gmail.mor.elevator.entiy.Elevator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ElevatorApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Building.class);
        context.register(Elevator.class);
        context.refresh();

        Building building = context.getBean("buildingBean", Building.class);
        building.start();

    }

}
