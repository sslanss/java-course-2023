package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class CommonTemporaryDirectory {
    protected static final Path TEMP_DIRECTORY = Path.of("src", "test", "resources", "testDir");

    @BeforeEach
    void createTempDirectory() throws IOException {
        if (!Files.exists(TEMP_DIRECTORY)) {
            Files.createDirectory(TEMP_DIRECTORY);
        }
    }

    protected void createTempFile(String fileName, String value) throws IOException {
        Path newFileName = TEMP_DIRECTORY.resolve(fileName);
        if (!Files.exists(newFileName)) {
            Files.createFile(newFileName);
        }
        if (!value.isEmpty()) {
            try (var writer = new BufferedWriter(new FileWriter(String.valueOf(newFileName)))) {
                var printWriter = new PrintWriter(writer);
                printWriter.print(value);
            }
        }
    }

    protected void createTempBytesFile(String fileName, byte[] bytes) throws IOException {
        Path newFileName = TEMP_DIRECTORY.resolve(fileName);
        if (!Files.exists(newFileName)) {
            Files.createFile(newFileName);
        }
        if (bytes.length > 0) {
            try (OutputStream os = new BufferedOutputStream(Files.newOutputStream(newFileName))) {
                os.write(bytes);
            }
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
