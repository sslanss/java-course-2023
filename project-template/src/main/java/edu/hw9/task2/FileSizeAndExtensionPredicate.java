package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;

public class FileSizeAndExtensionPredicate implements Predicate<Path> {
    private final long maxSize;
    private final String extension;

    public FileSizeAndExtensionPredicate(long maxSize, String extension) {
        this.maxSize = maxSize;
        this.extension = extension;
    }

    @Override
    public boolean test(Path path) {
        try {
            return Files.size(path) <= maxSize
                && path.getFileName()
                .toString()
                .endsWith(extension);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
