package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.RecursiveTask;

public class DirectoryFinder extends RecursiveTask<Set<Path>> {
    private final Path mainDirectory;

    private final int counter;

    public DirectoryFinder(Path mainDirectory, int counter) {
        this.mainDirectory = mainDirectory;
        this.counter = counter;
    }

    @Override
    protected Set<Path> compute() {
        Set<Path> resultDirectories = new HashSet<>();
        List<DirectoryFinder> subTasks = new ArrayList<>();
        int countFilesInDir = 0;

        try (DirectoryStream<Path> directories = Files.newDirectoryStream(mainDirectory)) {
            for (Path path : directories) {
                if (Files.isRegularFile(path)) {
                    countFilesInDir++;
                } else {
                    DirectoryFinder task = new DirectoryFinder(path, counter);
                    subTasks.add(task);
                    task.fork();
                }
            }
            if (countFilesInDir >= counter) {
                resultDirectories.add(mainDirectory);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (var task : subTasks) {
            resultDirectories.addAll(task.join());
        }
        return resultDirectories;
    }
}
