package com.gmail.mor.elevator.constants;

import lombok.Data;

@Data
public class AppManager {
    //Max floor count in building
    private static final int MAX_FLOOR_COUNT = 20;
    //Min floor count in building
    private static final int MIN_FLOOR_COUNT = 5;
    //Actual (random) floor count in building
    public static final int FLOOR_COUNT = (int) (Math.random() * (MAX_FLOOR_COUNT - MIN_FLOOR_COUNT) + MIN_FLOOR_COUNT);
    //Capacity of elevator
    public static final int ELEVATOR_CAPACITY = 5;


}
