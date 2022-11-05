package com.gmail.mor.elevator;

import com.gmail.mor.elevator.entiy.Floor;
import com.gmail.mor.elevator.entiy.Passenger;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//This class generates entities for another classes.
@Data
public class EntityGenerator {

    //Return list with automatically generated Passenger objects.
    //This method takes variable floorPositionNumber for correct logic of Passenger objects
    public static List<Passenger> generatePassengersList(int count) {
        List<Passenger> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(new Passenger());
        }
        return result;
    }

    //Returns list of automatically generated Floor objects
    //That floors would be
    public static List<Floor> generateFloorList(int count) {
        List<Floor> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(new Floor(i));
        }
        return result;
    }
}
