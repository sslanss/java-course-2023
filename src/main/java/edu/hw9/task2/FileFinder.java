package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

public class FileFinder extends RecursiveTask<List<Path>> {

    private final Path path;

    private final Predicate<Path> predicate;

    public FileFinder(Path path, Predicate<Path> predicate) {
        this.path = path;
        this.predicate = predicate;
    }

    @Override
    protected List<Path> compute() {
        List<Path> resultFiles = new ArrayList<>();
        List<FileFinder> subTasks = new ArrayList<>();
        try (DirectoryStream<Path> directories = Files.newDirectoryStream(path)) {
            for (Path file : directories) {
                if (Files.isDirectory(file)) {
                    FileFinder task = new FileFinder(file, predicate);
                    subTasks.add(task);
                    task.fork();
                } else {
                    if (predicate.test(file)) {
                        resultFiles.add(file);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (var task : subTasks) {
            resultFiles.addAll(task.join());
        }
        return resultFiles;
    }
}
