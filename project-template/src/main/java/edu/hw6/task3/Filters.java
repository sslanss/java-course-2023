package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filters {
    private Filters() {

    }

    public static final AbstractFilter REGULAR_FILE = Files::isRegularFile;
    public static final AbstractFilter READABLE = Files::isReadable;
    public static final AbstractFilter HIDDEN = Files::isHidden;
    public static final AbstractFilter EXECUTABLE = Files::isExecutable;
    public static final AbstractFilter WRITABLE = Files::isWritable;

    public static AbstractFilter largerThan(long size) {
        return path -> {
            try {
                return Files.size(path) > size;
            } catch (IOException e) {
                return false;
            }
        };
    }

    public static AbstractFilter hasSize(long size) {
        return path -> {
            try {
                return Files.size(path) == size;
            } catch (IOException e) {
                return false;
            }
        };
    }

    public static AbstractFilter smallerThan(long size) {
        return path -> {
            try {
                return Files.size(path) < size;
            } catch (IOException e) {
                return false;
            }
        };
    }

    public static AbstractFilter hasMagicNumber(byte... magicNumber) {
        return path -> {
            try (var input = Files.newInputStream(path)) {
                byte[] bytes = new byte[magicNumber.length];
                if (input.read(bytes) != magicNumber.length) {
                    return false;
                }
                return Arrays.equals(bytes, magicNumber);
            } catch (IOException e) {
                return false;
            }
        };
    }

    public static AbstractFilter globMatches(String glob) {
        return path -> {
            Pattern extension = Pattern.compile(glob + '$');
            Matcher matcher = extension.matcher(path.toString());
            return matcher.find();
        };
    }

    public static AbstractFilter regexContains(String regex) {
        return path -> {
            Pattern extension = Pattern.compile(regex);
            Matcher matcher = extension.matcher(path.toString());
            return matcher.find();
        };
    }
}
