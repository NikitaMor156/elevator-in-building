package com.gmail.mor.elevator.entiy;

import com.gmail.mor.elevator.constants.Conf;
import com.gmail.mor.elevator.generator.PassengerIdGenerator;
import lombok.Data;

@Data
public class Passenger {


    private int id = PassengerIdGenerator.getUniqueId();

    //Passenger destination floor where he needs to get
    private int destinationFloor;

    //Constructor
    public Passenger() {
        this.destinationFloor = (int) (Math.random() * Conf.FLOOR_COUNT);
    }
}
