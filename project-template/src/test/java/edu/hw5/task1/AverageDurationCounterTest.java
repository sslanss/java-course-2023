package edu.hw5.task1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class AverageDurationCounterTest {
    private static AverageDurationCounter averageDurationCounter;

    @BeforeAll
    public static void setUpCounter() {
        averageDurationCounter = new AverageDurationCounter();
    }

    @Test
    public void getAverageDurationShouldReturnCorrectDuration() {
        List<String> input = new ArrayList<>() {{
            add("2022-03-12, 20:20 - 2022-03-12, 23:50");
            add("2022-04-01, 21:30 - 2022-04-02, 01:20");
        }};
        String result = averageDurationCounter.getAverageDuration(input);
        Assertions.assertThat(result).isEqualTo("3ч 40м");

        input = new ArrayList<>() {{
            add("2022-03-12, 20:20 - 2022-03-12, 23:20");
            add("2022-04-01, 21:20 - 2022-04-02, 00:20");
        }};
        result = averageDurationCounter.getAverageDuration(input);
        Assertions.assertThat(result).isEqualTo("3ч");

        input = new ArrayList<>() {{
            add("2022-03-12, 20:20 - 2022-03-12, 20:50");
            add("2022-04-01, 21:30 - 2022-04-01, 21:50");
        }};
        result = averageDurationCounter.getAverageDuration(input);
        Assertions.assertThat(result).isEqualTo("25м");
    }

    @Test
    public void getAverageDurationShouldReturnCorrectDurationIfInputListIsPartlyCorrect() {
        List<String> input = new ArrayList<>() {{
            add("2022-03-12, 20:20 - 2022-03-12, 23:50");
            add("2022-04-01, 21:30 - 2022-04-02, 01:20");
            add("2022-04-02, 21:30 - 2022-04-02, 01:20");
        }};
        String result = averageDurationCounter.getAverageDuration(input);
        Assertions.assertThat(result).isEqualTo("3ч 40м");

    }

    @Test
    public void getAverageDurationShouldReturnCorrectDurationIfInputListContainsOutOfPeriodDates() {
        List<String>input = new ArrayList<>() {{
            add("2022-03-12, 20:20 - 2022-03-12, 23:50");
            add("2022-04-01, 21:30 - 2022-04-02, 01:20");
            add("2025-04-02, 21:30 - 2025-04-02, 01:20");
        }};
        String result = averageDurationCounter.getAverageDuration(input);
        Assertions.assertThat(result).isEqualTo("3ч 40м");

        input = new ArrayList<>() {{
            add("2022-03-12, 20:20 - 2022-03-12, 23:50");
            add("2022-04-01, 21:30 - 2022-04-02, 01:20");
            add("2009-04-02, 21:30 - 2009-04-02, 01:20");
        }};
        result = averageDurationCounter.getAverageDuration(input);
        Assertions.assertThat(result).isEqualTo("3ч 40м");
    }

}
