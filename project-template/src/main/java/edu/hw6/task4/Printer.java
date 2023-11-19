package edu.hw6.task4;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

public class Printer {
    private Printer() {

    }

    public static void print() {
        File file = new File(/*"D:\\Java_course\\java-course-2023" +
            "\\project-template\\src\\main\\java\\edu\\hw6\\task4\\*/"test.txt");
        try (PrintWriter stream = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream
            (new CheckedOutputStream(new FileOutputStream(file), new Adler32())), StandardCharsets.UTF_8))) {
            stream.println("Programming is learned by writing programs. ― Brian Kernighan");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
