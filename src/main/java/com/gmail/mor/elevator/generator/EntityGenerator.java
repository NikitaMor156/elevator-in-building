package com.gmail.mor.elevator.generator;

import com.gmail.mor.elevator.entiy.Floor;
import com.gmail.mor.elevator.entiy.Passenger;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//This class generates entities for another classes.
@Data
public class EntityGenerator {

    //Return list with automatically generated Passenger objects.
    //Usually used to fill list of passengers of Floor class objects.
    public static List<Passenger> generatePassengersList(int count) {
        List<Passenger> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(new Passenger());
        }
        return result;
    }

    //Returns list of automatically generated list of Floor objects
    //That floors' passenger lists would be automatically filled with Passenger objects
    public static List<Floor> generateFloorList(int count) {
        List<Floor> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(new Floor(i));
        }
        return result;
    }
}
