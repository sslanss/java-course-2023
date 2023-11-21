package edu.hw6;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

public class CommonTemporaryDirectory {
    //protected static final Path TEMP_DIRECTORY = Path.of("D:\\Java_labs\\test");

    protected static final Path TEMP_DIRECTORY = Path.of(System.getProperty("java.io.tmpdir") + "\\testTmpDir");


    @BeforeEach
    void createTempDirectory() throws IOException {
        if (!Files.exists(TEMP_DIRECTORY)) {
            Files.createDirectory(TEMP_DIRECTORY);
        }
    }

    protected void createTempFile(String fileName, String value) throws IOException{
        Path newFileName = TEMP_DIRECTORY.resolve(fileName);
        if (!Files.exists(newFileName)) {
            Files.createFile(newFileName);
        }
        try (var writer = new BufferedWriter(new FileWriter(String.valueOf(newFileName)))) {
            var printWriter = new PrintWriter(writer);
            printWriter.print(value);
        }
    }

    @AfterEach
    void deleteTempDirectory() throws IOException {
        try (var stream = Files.walk(TEMP_DIRECTORY)) {
            stream.sorted(Comparator.reverseOrder())
                .forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        throw new RuntimeException();
                    }
                });
        }
    }
}
