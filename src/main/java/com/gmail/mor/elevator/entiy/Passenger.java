package com.gmail.mor.elevator.entiy;

import com.gmail.mor.elevator.constants.Conf;
import com.gmail.mor.elevator.generator.PassengerIdGenerator;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Passenger {

    //Passenger unique id which provides correct logic of delete operations from Passenger collections (lists)
    private int id = PassengerIdGenerator.getUniqueId();

    //Passenger destination floor where he needs to get
    private int destinationFloor;

    //Constructor
    public Passenger() {
        this.destinationFloor = (int) (Math.random() * Conf.FLOOR_COUNT);
    }
}
