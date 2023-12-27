package edu.hw9;

import edu.hw9.task2.DirectoryFinder;
import java.nio.file.Path;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DirectoryFinderTest {
    private static final Path TEMP_DIRECTORY = Path.of("D:\\Java_course\\java-course-2023\\project-template" +
        "\\src\\test\\java\\edu\\hw9\\testDir");

    private static final Path TEMP_SUBDIRECTORY1 = Path.of("testDir2");

    private static final Path TEMP_SUBDIRECTORY2 = Path.of("testDir3");

    private static final Path TEMP_SUBDIRECTORY3 = Path.of("testDir4");

    private static DirectoryManager manager;

    //protected static final Path TEMP_DIRECTORY = Path.of(System.getProperty("java.io.tmpdir") + "\\testTmpDir");

    @BeforeAll
    public static void setUpTestDirectory() {
        manager = new DirectoryManager();
        manager.setDirectory(TEMP_DIRECTORY)
            .createFiles(2, ".txt", 10);

        manager.setExternalDirectory(TEMP_DIRECTORY)
            .setInnerDirectory(TEMP_SUBDIRECTORY1)
            .createFiles(2, ".txt", 20)
            .createFiles(3, ".dat", 10);

        manager.setInnerDirectory(TEMP_SUBDIRECTORY2)
            .createFiles(2, ".txt", 10);

        Path newExternalDirectory = TEMP_DIRECTORY.resolve(TEMP_SUBDIRECTORY1);

        manager.setExternalDirectory(newExternalDirectory)
            .setInnerDirectory(TEMP_SUBDIRECTORY3)
            .createFiles(3, ".txt", 20);

    }

    @Test
    public void directoryFinderShouldCorrectlyCountDirectories() {
        DirectoryFinder finder = new DirectoryFinder(TEMP_DIRECTORY, 3);
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            Set<Path> result = forkJoinPool.invoke(finder);
            Assertions.assertThat(result).containsExactlyInAnyOrder(
                TEMP_DIRECTORY.resolve(TEMP_SUBDIRECTORY1),
                TEMP_DIRECTORY.resolve(TEMP_SUBDIRECTORY1).resolve(TEMP_SUBDIRECTORY3)
            );
        }
    }

    @AfterAll
    public static void deleteTempDirectory() {
        manager.setExternalDirectory(TEMP_DIRECTORY)
            .deleteFiles();
    }
}
