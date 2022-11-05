package com.gmail.mor.elevator.entiy;

import com.gmail.mor.elevator.constants.AppManager;
import lombok.Data;

import javax.annotation.PostConstruct;

@Data
public class Passenger {

    //Passenger destination floor
    private int destinationFloor;
    //Number of actual passenger's floor
    private int position;

    //Constructor
    public Passenger() {
        this.destinationFloor = (int) (Math.random() * AppManager.FLOOR_COUNT);
    }
}
