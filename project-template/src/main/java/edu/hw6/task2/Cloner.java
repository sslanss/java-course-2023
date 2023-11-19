package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cloner {
    private Cloner() {
    }

    public static void clonePath(Path path) throws IOException {
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return;
        }
        Pattern pattern = Pattern.compile("(.*?)(( — копия)( \\((\\d+)\\))?)?(\\.[a-z]{2,})$");
        String fileName = path.getFileName().toString();
        Matcher matcher = pattern.matcher(fileName);
        if (matcher.find()) {
            String newFileName = matcher.group(1) +
                ((matcher.group(2) == null) ? " — копия"
                    : (matcher.group(3) + ((matcher.group(4) == null) ? " (2)" :
                    " (" + (Integer.parseInt(matcher.group(5)) + 1) + ")")))
                + matcher.group(6);
            Path destinationPath = path.resolveSibling(newFileName);
            Files.copy(path, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            FileTime now = FileTime.from(Instant.now());
            Files.setLastModifiedTime(destinationPath, now);
        }
    }
}
