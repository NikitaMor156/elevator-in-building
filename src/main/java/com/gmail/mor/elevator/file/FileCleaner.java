package com.gmail.mor.elevator.file;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//Class is used for deleting all the data from txt files.
@Data
@NoArgsConstructor
public class FileCleaner {

    //Clear file from text
    //Usually used to clear file after previous program run before new one.
    public static void clearFile(String fileName) {
        try (var writer = new PrintWriter(fileName)) {
            writer.print("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    //Clear file from text
    //Usually used to clear file after previous program run before new one.
    public static void clearFile(File file) {
        clearFile(file.getAbsolutePath());
    }

}
