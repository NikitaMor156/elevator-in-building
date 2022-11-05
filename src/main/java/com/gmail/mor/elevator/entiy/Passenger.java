package com.gmail.mor.elevator.entiy;

import com.gmail.mor.elevator.constants.AppManager;
import lombok.Data;

import javax.annotation.PostConstruct;

@Data
public class Passenger {

    //Passenger destination floor where he needs to get
    private int destinationFloor;

    //Constructor
    public Passenger() {
        this.destinationFloor = (int) (Math.random() * AppManager.FLOOR_COUNT);
    }
}
