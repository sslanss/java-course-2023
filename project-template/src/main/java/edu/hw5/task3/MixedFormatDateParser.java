package edu.hw5.task3;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MixedFormatDateParser extends DateParser {
    private static final Pattern pattern = Pattern.compile("^(\\d+) day|days ago$");

    @Override
    protected LocalDate tryParseDate(String string) {
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            return convertToDate(matcher, pattern);
        }
        return tryNext(string);
    }

    @Override
    protected LocalDate convertToDate(Matcher matcher, Pattern pattern) {
        return LocalDate.now().minusDays(Integer.parseInt(matcher.group(1)));
    }

    public MixedFormatDateParser() {
        //super(next);

    }

}
