package com.gmail.mor.elevator.entiy;

import com.gmail.mor.elevator.constants.AppManager;
import com.gmail.mor.elevator.manager.ElevatorLogicManager;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component("elevatorBean")
@NoArgsConstructor
public class Elevator {

    private int position = 0;
    private boolean isGoingUp = true;
    private List<Passenger> passengers = new ArrayList<>();
    private final int capacity = AppManager.ELEVATOR_CAPACITY;
    private List<Floor> floorList;
    private ElevatorLogicManager logic;

    public Elevator(List<Floor> floorList) {
        this.floorList = floorList;
        this.logic = new ElevatorLogicManager(this);
    }

}
