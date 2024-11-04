package edu.hw6.task4;

import edu.hw6.CommonTemporaryDirectory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrinterTest extends CommonTemporaryDirectory {
    private boolean tryToReadFile(Path path, String data) throws IOException {
        return data.equals(new String(Files.readAllBytes(path)));
    }

    @Test
    public void printShouldWorkCorrectlyIfFileDoesNotExist() throws IOException {
        Path file = TEMP_DIRECTORY.resolve("test.txt");
        Printer.print(file);
        Assertions.assertThat(tryToReadFile(
            file,
            "Programming is learned by writing programs. ― Brian Kernighan\r\n"
        )).isTrue();
    }

    @Test
    public void printShouldWorkCorrectlyIfFileExists() throws IOException {
        Path file = TEMP_DIRECTORY.resolve("test.txt");
        createTempFile(file.toString(), "");
        Printer.print(file);
        Assertions.assertThat(tryToReadFile(
            file,
            "Programming is learned by writing programs. ― Brian Kernighan\r\n"
        )).isTrue();
    }
}
