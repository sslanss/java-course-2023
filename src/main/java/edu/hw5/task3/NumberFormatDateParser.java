package edu.hw5.task3;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("MagicNumber")
public class NumberFormatDateParser extends DateParser {
    private static final Map<Pattern, Function<Matcher, LocalDate>> FUNCTION_MAP;

    static {
        FUNCTION_MAP = new HashMap<>();
        FUNCTION_MAP.put(
            Pattern.compile("^(\\d{4})-(\\d{1,2})-(\\d{1,2})$"),
            (Matcher matcher) -> LocalDate.of(Integer.parseInt(matcher.group(1)),
                Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3))
            )
        );
        FUNCTION_MAP.put(
            Pattern.compile("^(\\d{1,2})/(\\d{1,2})/(\\d{4})$"),
            (Matcher matcher) -> LocalDate.of(Integer.parseInt(matcher.group(3)),
                Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(1))
            )
        );
        FUNCTION_MAP.put(
            Pattern.compile("^(\\d{1,2})/(\\d{1,2})/(\\d{1,2})$"),
            (Matcher matcher) -> LocalDate.of(Integer.parseInt(matcher.group(3)) + 2000,
                Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(1))
            )
        );
    }

    @Override
    protected LocalDate tryParseDate(String string) {
        for (var pattern : FUNCTION_MAP.keySet()) {
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
            return FUNCTION_MAP.get(pattern).apply(matcher);
        } catch (DateTimeException e) {
            return null;
        }
    }

    public NumberFormatDateParser() {
    }

}
