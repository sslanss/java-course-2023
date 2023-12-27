package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public abstract class DateParser {

    public DateParser next;

    public static DateParser getChain(DateParser first, DateParser... last) {
        if (first == null) {
            return null;
        }
        DateParser head = first;
        for (DateParser next : last) {
            head.next = next;
            head = next;
        }
        return first;
    }

    protected abstract LocalDate tryParseDate(String string);

    protected abstract LocalDate convertToDate(Matcher matcher, Pattern pattern);

    protected LocalDate tryNext(String string) {
        if (next == null) {
            return null;
        }
        return next.tryParseDate(string);
    }

    public DateParser() {
    }

    public static Optional<LocalDate> parseDate(@NotNull String string) {
        DateParser parser = DateParser.getChain(new MixedFormatDateParser(), new StringFormatDateParser(),
            new NumberFormatDateParser()
        );
        LocalDate date = parser.tryParseDate(string);
        if (date != null) {
            return Optional.of(date);
        } else {
            return Optional.empty();
        }
    }
}
