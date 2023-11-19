package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filters {
    public static final AbstractFilter regularFile = Files::isRegularFile;
    public static final AbstractFilter readable = Files::isReadable;
    public static final AbstractFilter hidden = Files::isHidden;
    public static final AbstractFilter executable = Files::isExecutable;
    public static final AbstractFilter writable = Files::isWritable;

    public static AbstractFilter largerThan(long size) {
        return path -> {
            try {
                return Files.size(path) > size;
            } catch (IOException e) {
                return false;
            }
        };
    }

    public static AbstractFilter isSize(long size) {
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

    public static AbstractFilter magicNumbers(byte ...magicNumbers) {
        return path -> {
            //Pattern extension = Pattern.compile(glob + '$');
            //Matcher matcher = extension.matcher(path.toString());
            //return matcher.find();
            return false;
        };
    }

    public static AbstractFilter globMatches(String glob) {
        return path -> {
            Pattern extension = Pattern.compile(glob + '$');
            Matcher matcher = extension.matcher(path.toString());
            return matcher.find();
        };
    }

    public static AbstractFilter regexContains(String regex){
        return path -> {
            Pattern extension = Pattern.compile(regex);
            Matcher matcher = extension.matcher(path.toString());
            return matcher.find();
        };
    }
}
