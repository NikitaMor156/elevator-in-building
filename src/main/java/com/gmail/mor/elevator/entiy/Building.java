package com.gmail.mor.elevator.entiy;

import com.gmail.mor.elevator.EntityGenerator;
import com.gmail.mor.elevator.constants.AppManager;
import com.gmail.mor.elevator.entiy.Floor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Building {

    private List<Floor> floorList;
    private Elevator elevator;

    public Building(Elevator elevator) {
        this.elevator = new Elevator();
        floorList = EntityGenerator.generateFloorList(AppManager.FLOOR_COUNT);
    }

    public boolean isProcedureDone() {
        boolean isDone = true;
        List<Passenger> allPassengers = getAllPassengers();
        for (Passenger p : allPassengers) {
            if (p.getDestinationFloor() != p.getPosition()) {
                isDone = false;
                break;
            }
        }
        return isDone;
    }

    public List<Passenger> getAllPassengers() {
        List<Passenger> result = new ArrayList<>();
        for (Floor f : floorList) {
            for (Passenger p : f.getPassengerList()) {
                result.add(p);
            }
        }
        return result;
    }

    public void start() {
        while (!isProcedureDone()) {
            elevator.move();
        }
    }

}
