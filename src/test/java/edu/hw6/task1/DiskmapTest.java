package edu.hw6.task1;

import edu.hw6.CommonTemporaryDirectory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DiskmapTest extends CommonTemporaryDirectory {
    private static Stream<Arguments> keyValueArguments() {
        return Stream.of(
            Arguments.of("key1.txt", "value1"),
            Arguments.of("key2.txt", "value2"),
            Arguments.of("key3.txt", "value3")
        );
    }

    private boolean isMapAndDiskAreTheSame(Diskmap diskmap) throws IOException {
        try (Stream<Path> streamPaths = Files.walk(CommonTemporaryDirectory.TEMP_DIRECTORY)) {
            List<Path> paths = streamPaths.filter(path -> !Files.isDirectory(path))
                .toList();
            for (var path : paths) {
                try {
                    String data = new String(Files.readAllBytes(path));
                    if (!data.equals(diskmap.get(path.getFileName().toString()))) {
                        return false;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return paths.size() == diskmap.size();
        }
    }

    @ParameterizedTest
    @MethodSource("keyValueArguments")
    public void entryShouldCreatesOnDiskInCaseItsAddingToTheDiskmap(String key, String value) throws IOException {
        Diskmap diskmap = new Diskmap(TEMP_DIRECTORY);
        diskmap.put(key, value);
        Assertions.assertThat(isMapAndDiskAreTheSame(diskmap)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("keyValueArguments")
    public void entryShouldDeletesOnDiskInCaseItsDeletingFromTheDiskmap(String key, String value) throws IOException {
        Diskmap diskmap = new Diskmap(TEMP_DIRECTORY);
        diskmap.putAll(keyValueArguments()
            .collect(Collectors.toMap(
                args -> (String) args.get()[0],
                args -> (String) args.get()[1]
            )));
        Assertions.assertThat(diskmap.remove(key)).isEqualTo(value);
        Assertions.assertThat(isMapAndDiskAreTheSame(diskmap)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("keyValueArguments")
    public void diskmapShouldCreatesCorrectlyWithDirectory() throws IOException {
        Diskmap diskmap = new Diskmap(TEMP_DIRECTORY);
        Assertions.assertThat(isMapAndDiskAreTheSame(diskmap)).isTrue();

        createTempFile("key1.txt", "value1");
        createTempFile("key2.txt", "value2");
        diskmap = new Diskmap(TEMP_DIRECTORY);
        Assertions.assertThat(isMapAndDiskAreTheSame(diskmap)).isTrue();
    }
}
