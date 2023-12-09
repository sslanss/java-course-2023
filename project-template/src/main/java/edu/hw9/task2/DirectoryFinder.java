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

    private final static int REQUIRED_NUMBER_OF_FILES = 5;

    private int counter;

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
            counter += countFilesInDir;

            if (counter >= REQUIRED_NUMBER_OF_FILES) {
                resultDirectories.add(mainDirectory);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*int tasksSize = subTasks.size();
        int i = 0;
        while (counter<REQUIRED_NUMBER_OF_FILES && i < tasksSize){
            resultDirectories.addAll(subTasks.get(i).join());
            counter += subTasks.get(i).counter;
            i++;
        }
        if (counter>=REQUIRED_NUMBER_OF_FILES)
            resultDirectories.add(mainDirectory);*/
        for (var task : subTasks) {
            resultDirectories.addAll(task.join());
            counter += task.counter;
            //здесь подумать, произойдет добавление главной директории несколько раз :)
            if (counter >= REQUIRED_NUMBER_OF_FILES) {
                resultDirectories.add(mainDirectory);
            }
        }
        return resultDirectories;
    }
}
