package edu.hw6.task2;

import edu.hw6.CommonTemporaryDirectory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClonerTest extends CommonTemporaryDirectory {

    private boolean tryToOpenClonedFile(Path path, String data) throws IOException {
        return data.equals(new String(Files.readAllBytes(path)));
    }

    @Test
    public void clonerShouldCreateCorrectCopyFiles() throws IOException {
        createTempFile("test.txt", "value");
        Path file = TEMP_DIRECTORY.resolve("test.txt");
        Path copiedFile = file.resolveSibling("test — копия.txt");

        Cloner.clonePath(file);
        Assertions.assertThat(tryToOpenClonedFile(
            copiedFile,
            "value"
        )).isTrue();

        Cloner.clonePath(copiedFile);
        copiedFile = copiedFile.resolveSibling("test — копия (2).txt");
        Assertions.assertThat(tryToOpenClonedFile(
            copiedFile,
            "value"
        )).isTrue();

        Cloner.clonePath(copiedFile);
        copiedFile = copiedFile.resolveSibling("test — копия (3).txt");
        Assertions.assertThat(tryToOpenClonedFile(
            copiedFile,
            "value"
        )).isTrue();
    }

}
