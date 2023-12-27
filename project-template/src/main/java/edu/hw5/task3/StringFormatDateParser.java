package edu.hw5.task3;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringFormatDateParser extends DateParser {
    private static final Map<Pattern, LocalDate> PATTERN_LOCAL_DATE_MAP;

    static {
        PATTERN_LOCAL_DATE_MAP = new HashMap<>();
        PATTERN_LOCAL_DATE_MAP.put(Pattern.compile("^tomorrow$"), LocalDate.now().plusDays(1));
        PATTERN_LOCAL_DATE_MAP.put(Pattern.compile("^today$"), LocalDate.now());
        PATTERN_LOCAL_DATE_MAP.put(Pattern.compile("^yesterday$"), LocalDate.now().minusDays(1));
    }

    @Override
    protected LocalDate tryParseDate(String string) {
        for (var pattern : PATTERN_LOCAL_DATE_MAP.keySet()) {
            Matcher matcher = pattern.matcher(string);
            if (matcher.find()) {
                return convertToDate(matcher, pattern);
            }
        }
        return tryNext(string);
    }

    @Override
    protected LocalDate convertToDate(Matcher matcher, Pattern pattern) {
        return PATTERN_LOCAL_DATE_MAP.get(pattern);
    }

    public StringFormatDateParser() {
        //super(next);
    }

}
