package com.gmail.mor.elevator.file;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

@Data
@NoArgsConstructor
public class FileCleaner {

    //This method is used to clear file before (to delete old output from previous run)
    public static void clearFile(String fileName) {
        try (var writer = new PrintWriter(fileName)) {
            writer.print("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
