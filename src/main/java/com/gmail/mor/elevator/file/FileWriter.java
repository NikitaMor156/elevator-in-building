package com.gmail.mor.elevator.file;

import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

//Class is used to write strings to txt files
@Data
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
    //Appends string to the file.
    public static void appendStringToFile(String text, File file) {
        appendStringToFile(text, file.getAbsolutePath());
    }

}
