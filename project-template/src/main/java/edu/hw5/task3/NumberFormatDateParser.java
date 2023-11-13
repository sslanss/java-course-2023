package edu.hw5.task3;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberFormatDateParser extends DateParser {
    private static final Map<Pattern, Function<Matcher, LocalDate>> patterns;

    static {
        patterns = new HashMap<>();
        patterns.put(Pattern.compile("^(\\d{4})-(\\d{1,2})-(\\d{1,2})$"),
            (Matcher matcher)->LocalDate.of(Integer.parseInt(matcher.group(1)),
                Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3))));
        patterns.put(Pattern.compile("^(\\d{1,2})/(\\d{1,2})/(\\d{4})$"),
            (Matcher matcher)->LocalDate.of(Integer.parseInt(matcher.group(3)),
                Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(1))));
        patterns.put(Pattern.compile("^(\\d{1,2})/(\\d{1,2})/(\\d{1,2})$"),
            (Matcher matcher)->LocalDate.of(Integer.parseInt(matcher.group(3)) + 2000,
                Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(1))));
    }

    @Override
    protected LocalDate tryParseDate(String string) {
        for (var pattern: patterns.keySet()) {
            Matcher matcher = pattern.matcher(string);
            if (matcher.find()) {
                return convertToDate(matcher, pattern);
            }
        }
        return tryNext(string);
    }

    @Override
    protected LocalDate convertToDate(Matcher matcher, Pattern pattern) {
        try {
            return patterns.get(pattern).apply(matcher);
        }
        catch(DateTimeException e)
        {
            return null;
        }
    }

    public NumberFormatDateParser() {
    }


}
