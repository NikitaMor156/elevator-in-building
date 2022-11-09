package com.gmail.mor.elevator.generator;

public class PassengerIdGenerator {

    //Dynamic variable which is responsible for id generation for Passenger objects
    private static int generatedUniqueIdForPassenger = 0;

    //Returns unique integer number
    //Usually used to set unique id for Passenger objects
    public static int getUniqueId(){
        generatedUniqueIdForPassenger += 1;
        return generatedUniqueIdForPassenger;
    }
}
