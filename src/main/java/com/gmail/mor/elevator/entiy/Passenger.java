package com.gmail.mor.elevator.entiy;

import com.gmail.mor.elevator.constants.AppManager;
import lombok.Data;

import javax.annotation.PostConstruct;

@Data
public class Passenger {

    private int destinationFloor;
    private int position;

    public Passenger(int position) {
        this.position = position;
        this.destinationFloor = (int) (Math.random() * AppManager.FLOOR_COUNT);
    }
}
