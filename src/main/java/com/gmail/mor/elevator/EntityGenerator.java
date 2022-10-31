package com.gmail.mor.elevator;

import com.gmail.mor.elevator.entiy.Floor;
import com.gmail.mor.elevator.entiy.Passenger;

import java.util.ArrayList;
import java.util.List;

public class EntityGenerator {

    public static List<Passenger> generatePassengersList(int count, int floorPositionNibmer) {
        List<Passenger> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(new Passenger(floorPositionNibmer));
        }
        return result;
    }

    public static List<Floor> generateFloorList(int count) {
        List<Floor> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(new Floor(i));
        }
        return result;
    }
}
