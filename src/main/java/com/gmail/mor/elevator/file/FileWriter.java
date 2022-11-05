package com.gmail.mor.elevator.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileWriter {

    //Appends string to the file.
    public static void appendStringToFile(String text, String fileName) {
        try {
            Files.write(Paths.get(fileName),
                    text.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
