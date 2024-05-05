package edu.hw9;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

public class DirectoryManager {
    private Path innerDirectory;
    private Path externalDirectory;

    public DirectoryManager() {

    }

    public DirectoryManager setExternalDirectory(Path externalDirectory) {
        this.externalDirectory = externalDirectory;
        if (!Files.exists(externalDirectory)) {
            try {
                Files.createDirectory(externalDirectory);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return this;
    }

    public DirectoryManager setInnerDirectory(Path directory) {
        this.innerDirectory = externalDirectory.resolve(directory);
        if (!Files.exists(innerDirectory)) {
            try {
                Files.createDirectory(innerDirectory);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return this;
    }

    public DirectoryManager setDirectory(Path innerDirectory) {
        this.innerDirectory = innerDirectory;
        if (!Files.exists(innerDirectory)) {
            try {
                Files.createDirectory(innerDirectory);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return this;
    }

    public DirectoryManager deleteFiles() {
        try (var stream = Files.walk(externalDirectory)) {
            stream.sorted(Comparator.reverseOrder())
                .forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        throw new RuntimeException();
                    }
                });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public DirectoryManager createFiles(int count, String extension, long fileSizeInBytes) {
        for (int i = 0; i < count; i++) {
            Path currentFileName = innerDirectory.resolve(i + 1 + extension);
            if (!Files.exists(currentFileName)) {
                try {
                    Files.createFile(currentFileName);
                    readBytesToFile(currentFileName, fileSizeInBytes);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return this;
    }

    private void readBytesToFile(Path filePath, long fileSizeInBytes) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filePath.toFile());
             FileChannel channel = fos.getChannel()) {

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            byte[] data = new byte[1024];

            while (fileSizeInBytes > 0) {
                int bytesToWrite = (int) Math.min(data.length, fileSizeInBytes);
                buffer.put(data, 0, bytesToWrite);
                buffer.flip();
                channel.write(buffer);
                buffer.clear();
                fileSizeInBytes -= bytesToWrite;
            }
        }
    }
}
