package com.gmail.mor.elevator.constants;

import lombok.Data;

@Data
public class AppManager {
    private static final int MAX_FLOOR_COUNT = 20;
    private static final int MIN_FLOOR_COUNT = 5;
    public static final int FLOOR_COUNT = (int) (Math.random() * (MAX_FLOOR_COUNT - MIN_FLOOR_COUNT) + MIN_FLOOR_COUNT);
    public static final int ELEVATOR_MAX_COUNT = 5;



}
