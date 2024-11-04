package edu.hw9;

import edu.hw9.task2.FileFinder;
import edu.hw9.task2.FileSizeAndExtensionPredicate;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FileFinderTest {
    private static final Path TEMP_DIRECTORY = Path.of("src", "test", "resources", "testDir");

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
    public void fileFinderShouldCorrectlyCountFiles() {
        FileFinder finder = new FileFinder(
            TEMP_DIRECTORY,
            new FileSizeAndExtensionPredicate(10, ".txt")
        );
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            List<Path> result = forkJoinPool.invoke(finder);
            Assertions.assertThat(result).containsExactlyInAnyOrder(TEMP_DIRECTORY.resolve("1.txt"),
                TEMP_DIRECTORY.resolve("2.txt"), TEMP_DIRECTORY
                    .resolve(TEMP_SUBDIRECTORY2.resolve("1.txt")),
                TEMP_DIRECTORY.resolve(TEMP_SUBDIRECTORY2.resolve("2.txt"))
            );
        }
    }

    @AfterAll
    public static void deleteTempDirectory() {
        manager.setExternalDirectory(TEMP_DIRECTORY)
            .deleteFiles();
    }
}
