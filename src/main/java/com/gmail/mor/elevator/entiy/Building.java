package com.gmail.mor.elevator.entiy;

import com.gmail.mor.elevator.EntityGenerator;
import com.gmail.mor.elevator.constants.AppManager;
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

    public boolean areAllPassengersOnTheirDestinationFloors() {
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
        result.addAll(elevator.getPassengerList());
        return result;
    }

    public List<Passenger> getFloorPassengers(int index){
        return floorList.get(index).getPassengerList();
    }

    public Floor getFloor(int index){
        return floorList.get(index);
    }

    public List<Floor> getFloors(int fromIndex, int toIndex){
        return floorList.subList(fromIndex, toIndex);
    }

    public void start() {
        while (!areAllPassengersOnTheirDestinationFloors()) {
            FrontEndMaker.printBuilding(this);
            FrontEndMaker.writeFrontEndToLogFile(this);
            elevator.changeDirectionOfMoveIfItIsNecessary();
            elevator.dropAndPickUpPassengers();
            elevator.move();
            System.out.println(elevator.getPosition());
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
