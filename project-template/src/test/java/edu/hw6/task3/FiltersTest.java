package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import edu.hw6.CommonTemporaryDirectory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static edu.hw6.task3.Filters.READABLE;
import static edu.hw6.task3.Filters.REGULAR_FILE;
import static edu.hw6.task3.Filters.globMatches;
import static edu.hw6.task3.Filters.hasMagicNumber;
import static edu.hw6.task3.Filters.hasSize;
import static edu.hw6.task3.Filters.largerThan;
import static edu.hw6.task3.Filters.regexContains;
import static edu.hw6.task3.Filters.smallerThan;

public class FiltersTest extends CommonTemporaryDirectory {
    @BeforeEach
    void createTmpFiles() throws IOException {
        createTempBytesFile("test2.png", new byte[] {89, 'P', 'N', 'G'});
        createTempBytesFile("test3.png", new byte[] {89, 'P', 'N', 'G'});
        createTempBytesFile("test(2).txt", new byte[100]);
        createTempBytesFile("test.dat.txt", new byte[10]);
        createTempFile("test-final.dat", "");
        createTempFile("20-12-2023.dat", "valueString");
        createTempFile("19-12-2023.dat", "");
    }

    @Test
    public void attributeFiltersShouldChooseCorrectFiles() throws IOException {
        DirectoryStream.Filter<Path> filter =
            REGULAR_FILE
                .and(READABLE);

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEMP_DIRECTORY, filter)) {
            List<String> fileNames = new ArrayList<>();
            for (Path path : entries) {
                fileNames.add(path.getFileName().toString());
            }
            Assertions.assertThat(fileNames).containsExactlyInAnyOrder(
                "test-final.dat",
                "20-12-2023.dat",
                "19-12-2023.dat",
                "test(2).txt",
                "test.dat.txt",
                "test2.png",
                "test3.png"
            );
        }
    }

    @Test
    public void sizeFilterShouldChooseCorrectFiles() throws IOException {
        DirectoryStream.Filter<Path> filter =
            hasSize(10);

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEMP_DIRECTORY, filter)) {
            List<String> fileNames = new ArrayList<>();
            for (Path path : entries) {
                fileNames.add(path.getFileName().toString());
            }
            Assertions.assertThat(fileNames).containsExactlyInAnyOrder(
                "test.dat.txt"
            );
        }

        filter =
            smallerThan(-10);

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEMP_DIRECTORY, filter)) {
            List<String> fileNames = new ArrayList<>();
            for (Path path : entries) {
                fileNames.add(path.getFileName().toString());
            }
            Assertions.assertThat(fileNames).isEmpty();
        }

        filter =
            largerThan(20);

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEMP_DIRECTORY, filter)) {
            List<String> fileNames = new ArrayList<>();
            for (Path path : entries) {
                fileNames.add(path.getFileName().toString());
            }
            Assertions.assertThat(fileNames).containsExactlyInAnyOrder("test(2).txt");
        }
    }

    @Test
    public void magicNumbersFilterShouldChooseCorrectFiles() throws IOException {
        DirectoryStream.Filter<Path> filter =
            hasMagicNumber(new byte[] {89, 'P', 'N', 'G'});

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEMP_DIRECTORY, filter)) {
            List<String> fileNames = new ArrayList<>();
            for (Path path : entries) {
                fileNames.add(path.getFileName().toString());
            }
            Assertions.assertThat(fileNames).containsExactlyInAnyOrder("test2.png", "test3.png");
        }
    }

    @Test
    public void globMatchesFilterShouldChooseCorrectFiles() throws IOException {
        DirectoryStream.Filter<Path> filter =
            globMatches(".dat");

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEMP_DIRECTORY, filter)) {
            List<String> fileNames = new ArrayList<>();
            for (Path path : entries) {
                fileNames.add(path.getFileName().toString());
            }
            Assertions.assertThat(fileNames).containsExactlyInAnyOrder(
                "test-final.dat",
                "20-12-2023.dat",
                "19-12-2023.dat"
            );
        }

        filter = globMatches(".xml");

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEMP_DIRECTORY, filter)) {
            List<String> fileNames = new ArrayList<>();
            for (Path path : entries) {
                fileNames.add(path.getFileName().toString());
            }
            Assertions.assertThat(fileNames).isEmpty();
        }
    }

    @Test
    public void regexContainsFilterShouldChooseCorrectFiles() throws IOException {
        DirectoryStream.Filter<Path> filter =
            regexContains("[-]");

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEMP_DIRECTORY, filter)) {
            List<String> fileNames = new ArrayList<>();
            for (Path path : entries) {
                fileNames.add(path.getFileName().toString());
            }
            Assertions.assertThat(fileNames).containsExactlyInAnyOrder(
                "test-final.dat",
                "20-12-2023.dat",
                "19-12-2023.dat"
            );
        }
    }
    @Test
    public void severalFiltersShouldChooseCorrectFiles() throws IOException {
        DirectoryStream.Filter<Path> filter =
            regexContains("\\d")
                .and(smallerThan(10))
                .and(globMatches(".dat"));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEMP_DIRECTORY, filter)) {
            List<String> fileNames = new ArrayList<>();
            for (Path path : entries) {
                fileNames.add(path.getFileName().toString());
            }
            Assertions.assertThat(fileNames).containsExactlyInAnyOrder(
                "19-12-2023.dat"
            );
        }
    }
}
