package com.gmail.mor.elevator.generator;

public class PassengerIdGenerator {

    private static int generatedUniqueIdForPassenger = 0;

    public static int getUniqueId(){
        generatedUniqueIdForPassenger += 1;
        return generatedUniqueIdForPassenger;
    }
}
