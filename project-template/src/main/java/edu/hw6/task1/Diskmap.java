package edu.hw6.task1;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Diskmap implements Map<String, String> {

    private final Map<String, String> filesMap;

    private final Path directory;

    public Diskmap(Path directory) throws IOException {
        filesMap = new HashMap<>();
        this.directory = directory.toAbsolutePath();
        if (Files.exists(this.directory)) {
            loadFilesToMap();
        } else {
            Files.createDirectory(this.directory);
        }
    }

    private void loadFilesToMap() throws IOException {
        try (var filesStream = Files.list(directory)) {
            filesStream.filter(path -> !Files.isDirectory(path))
                    .forEach(path -> {
                        try {
                            String data = new String(
                                    Files.readAllBytes(path));
                            filesMap.put(path.getFileName().toString(), data);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
    }

    @Override
    public int size() {
        return filesMap.size();
    }

    @Override
    public boolean isEmpty() {
        return filesMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return filesMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return filesMap.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return filesMap.get(key);
    }

    @Override
    public String put(String key, String value) {
        Path newFileName = directory.resolve(key);
        try {
            if (!Files.exists(newFileName)) {
                Files.createFile(newFileName);
            }
            try (var writer = new BufferedWriter(new FileWriter(String.valueOf(newFileName)))) {
                var printWriter = new PrintWriter(writer);
                printWriter.print(value);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return filesMap.put(key, value);
    }

    @Override
    public String remove(Object key) {
        Path deletedFileName = directory.resolve((String) key);
        String deletedValue = filesMap.remove(key);
        if (deletedValue != null) {
            try {
                Files.delete(deletedFileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return deletedValue;
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
        m.forEach(this::put);
    }

    @Override
    public void clear() {
        filesMap.keySet().forEach(this::remove);
    }

    @Override
    public @NotNull Set<String> keySet() {
        return filesMap.keySet();
    }

    @Override
    public @NotNull Collection<String> values() {
        return filesMap.values();
    }

    @Override
    public @NotNull Set<Entry<String, String>> entrySet() {
        return filesMap.entrySet();
    }
}
