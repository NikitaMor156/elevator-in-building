package com.gmail.mor.elevator.entiy;

import com.gmail.mor.elevator.generator.EntityGenerator;
import com.gmail.mor.elevator.constants.Conf;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

//Building is class which contains list of Floor objects where passengers are and Elevator
//object which provides transportation of passengers after start() method of Building class is executed
@Data
@Component("buildingBean")
public class Building {

    //List of floors in this building
    private List<Floor> floors;

    //Elevator of this building
    // @Autowired
    private Elevator elevator;

    public Building() {
        //Generate random count of floors (floor list).
        //This floors will be automatically filled with random count of passengers.
        floors = EntityGenerator.generateFloorList(Conf.FLOOR_COUNT);
        elevator = new Elevator(floors);
    }
}
