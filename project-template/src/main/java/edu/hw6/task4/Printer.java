package edu.hw6.task4;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

public class Printer {
    private Printer() {

    }

    public static void print(Path fileName) {
        File file = new File(fileName.toString());
        try (PrintWriter stream = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(
            new CheckedOutputStream(new FileOutputStream(file), new Adler32())), StandardCharsets.UTF_8))) {
            stream.println("Programming is learned by writing programs. ― Brian Kernighan");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
