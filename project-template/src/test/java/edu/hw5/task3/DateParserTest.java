package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DateParserTest {
    @Test
    public void testGetChainWithMultipleParsers() {
        DateParser first = new MixedFormatDateParser();
        DateParser second = new StringFormatDateParser();
        DateParser third = new NumberFormatDateParser();

        DateParser parser = DateParser.getChain(first, second, third);

        Assertions.assertThat(parser.next).isEqualTo(second);
        Assertions.assertThat(second.next).isEqualTo(third);
        Assertions.assertThat(third.next).isNull();
    }

    @Test
    public void testGetChainWithSingleParser() {
        DateParser first = new MixedFormatDateParser();

        DateParser.getChain(first);

        Assertions.assertThat(first.next).isNull();
    }

    @Test
    public void testGetChainWithNullParsers() {
        DateParser result = DateParser.getChain(null, null);
        Assertions.assertThat(result).isNull();
    }

    @Test
    public void testParseMixedFormatDate() {
        Optional<LocalDate> parsedDate = DateParser.parseDate("1 day ago");
        LocalDate currentDate = LocalDate.now().minusDays(1);

        Assertions.assertThat(parsedDate.get()).isEqualTo(LocalDate.of(currentDate.getYear(),
            currentDate.getMonth(), currentDate.getDayOfMonth()
        ));

        parsedDate = DateParser.parseDate("32 days ago");
        currentDate = LocalDate.now().minusDays(32);

        Assertions.assertThat(parsedDate.get()).isEqualTo(LocalDate.of(currentDate.getYear(),
            currentDate.getMonth(), currentDate.getDayOfMonth()
        ));
    }

    @Test
    public void testParseNumberFormatDate() {
        Optional<LocalDate> parsedDate = DateParser.parseDate("3/2/2020");
        Assertions.assertThat(parsedDate.get()).isEqualTo(LocalDate.of(2020, 2, 3));

        parsedDate = DateParser.parseDate("3/2/20");
        Assertions.assertThat(parsedDate.get()).isEqualTo(LocalDate.of(2020, 2, 3));

        parsedDate = DateParser.parseDate("2020-2-3");
        Assertions.assertThat(parsedDate.get()).isEqualTo(LocalDate.of(2020, 2, 3));

        parsedDate = DateParser.parseDate("2020-12-13");
        Assertions.assertThat(parsedDate.get()).isEqualTo(LocalDate.of(2020, 12, 13));

        parsedDate = DateParser.parseDate("2020-13-13");
        Assertions.assertThat(parsedDate).isEmpty();
    }

    @Test
    public void testParseStringFormatDate() {
        LocalDate date = LocalDate.now().plusDays(1);

        Optional<LocalDate> parsedDate = DateParser.parseDate("tomorrow");
        Assertions.assertThat(parsedDate.get()).isEqualTo(date);

        date = LocalDate.now().minusDays(1);
        parsedDate = DateParser.parseDate("yesterday");
        Assertions.assertThat(parsedDate.get()).isEqualTo(date);

        date = LocalDate.now();
        parsedDate = DateParser.parseDate("today");
        Assertions.assertThat(parsedDate.get()).isEqualTo(date);
    }

    @Test
    public void testParseIncorrectString() {
        Optional<LocalDate> parsedDate = DateParser.parseDate("33");
        Assertions.assertThat(parsedDate).isEmpty();

        parsedDate = DateParser.parseDate("ssss");
        Assertions.assertThat(parsedDate).isEmpty();

        parsedDate = DateParser.parseDate("2-12-2020");
        Assertions.assertThat(parsedDate).isEmpty();
    }
}
