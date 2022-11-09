package com.gmail.mor.elevator.entiy;

import com.gmail.mor.elevator.constants.Conf;
import com.gmail.mor.elevator.manager.ElevatorMoveLogicManager;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//An elevator that operates inside the building and transports passengers
@Data
@Component("elevatorBean") //delete
@NoArgsConstructor //delete
public class Elevator {

    //Elevator current position
    private int position = 0;
    //Elevator's move direction
    private boolean isGoingUp = true;
    //Elevator's passengers
    private List<Passenger> passengers = new ArrayList<>();
    //Capacity of elevator
    private final int capacity = Conf.ELEVATOR_CAPACITY;
    //List of floors where the elevator is located
    private List<Floor> floorList;
    //Command center of elevator's work logic
    //All the operations as move, change firection of movement,
    private ElevatorMoveLogicManager logic;

    //Constructor
    public Elevator(List<Floor> floorList) {
        this.floorList = floorList;
        this.logic = new ElevatorMoveLogicManager(this);
    }

}
