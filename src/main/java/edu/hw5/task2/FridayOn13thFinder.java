package edu.hw5.task2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;

public class FridayOn13thFinder {

    private final static TemporalAdjuster NEXT_13TH_FRIDAY = TemporalAdjusters.ofDateAdjuster(date -> {
        if (date.getDayOfMonth() > 13) {
            date = date.plusMonths(1);
            date = date.withDayOfMonth(13);
        } else if (date.getDayOfMonth() < 13) {
            date = date.withDayOfMonth(13);
        } else {
            date = date.plusMonths(1);
        }
        boolean isFound = false;
        while (!isFound) {
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                isFound = true;
            } else {
                date = date.plusMonths(1);
            }
        }
        return date;
    });

    private FridayOn13thFinder() {

    }

    public static List<LocalDate> getAllFridaysOn13th(int year) {
        if (year < 0) {
            return new ArrayList<>();
        }
        LocalDate firstDay = LocalDate.of(year, 1, 1);
        LocalDate lastDay = LocalDate.of(year, 12, 31);
        long daysBetween = firstDay.until(lastDay, ChronoUnit.DAYS);
        return Stream.iterate(firstDay, date -> date.plusDays(1))
            .limit(daysBetween)
            .filter((date -> date.getDayOfMonth() == 13 && date.getDayOfWeek() == DayOfWeek.FRIDAY))
            .toList();
    }

    public static LocalDate getNextFridayOn13th(@NotNull LocalDate date) {
        return date.with(NEXT_13TH_FRIDAY);
    }
}
