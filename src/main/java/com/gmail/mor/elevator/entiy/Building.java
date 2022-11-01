package com.gmail.mor.elevator.entiy;

import com.gmail.mor.elevator.EntityGenerator;
import com.gmail.mor.elevator.constants.AppManager;
import com.gmail.mor.elevator.entiy.Floor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component("buildingBean")
public class Building {

    private List<Floor> floorList;
    @Autowired
    private Elevator elevator;

    public Building() {
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
        return isDone && elevator.getPassengerList().isEmpty();
    }

    public List<Passenger> getAllPassengers() {
        List<Passenger> result = new ArrayList<>();
        for (Floor f : floorList) {
            result.addAll(f.getPassengerList());
        }
        return result;
    }

    public void start() {
        while (!isProcedureDone()) {
            FrontEndMaker.printBuilding(this);
            FrontEndMaker.writeFrontEndToLogFile(this);
            elevator.dropOffPassengers();
            elevator.takePassengers();
            elevator.move();
        }
        FrontEndMaker.printBuilding(this);
        FrontEndMaker.writeFrontEndToLogFile(this);
    }

    @Override
    public String toString() {
        return "Building{" +
                "floorList=" + floorList +
                ", elevator=" + "AVOID OF DEATH LOOP CAUSED BY CIRCULAR DEPENDENCY!" +
                '}';
    }
}
