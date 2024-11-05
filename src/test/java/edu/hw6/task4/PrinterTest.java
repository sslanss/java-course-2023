package edu.hw6.task4;

import edu.hw6.CommonTemporaryDirectory;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrinterTest extends CommonTemporaryDirectory {
    private boolean tryToReadFile(Path path, String data) throws IOException {
        String actualData = Files.readString(path, StandardCharsets.UTF_8);
        return data.equals(actualData);
    }

    @Test
    public void printShouldWorkCorrectlyIfFileDoesNotExist() throws IOException {
        Path file = TEMP_DIRECTORY.resolve("test.txt");
        Printer.print(file);
        Assertions.assertThat(tryToReadFile(
            file,
            "Programming is learned by writing programs. ― Brian Kernighan" + System.lineSeparator()
        )).isTrue();
    }

    @Test
    public void printShouldWorkCorrectlyIfFileExists() throws IOException {
        Path file = TEMP_DIRECTORY.resolve("test.txt");
        createTempFile("test.txt", "");
        Printer.print(file);
        Assertions.assertThat(tryToReadFile(
            file,
            "Programming is learned by writing programs. ― Brian Kernighan" + System.lineSeparator()
        )).isTrue();
    }
}
