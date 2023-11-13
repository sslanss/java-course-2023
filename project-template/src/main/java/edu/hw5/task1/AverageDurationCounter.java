package edu.hw5.task1;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public class AverageDurationCounter {

    private final LocalDateTime clubOpeningDate = LocalDateTime.of(
        2010, 12, 1, 10, 0);
    private final Pattern session =
        Pattern.compile("(\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2}) - (\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2})");

    public String getAverageDuration(@NotNull List<String> input) {
        double averageDurationInMillis =
            input.stream()
                .map(this::getDuration)
                .filter((duration) -> !duration.isZero())
                .mapToLong(Duration::toMillis)
                .average().orElse(0);
        return formatDuration(Duration.ofMillis((long) averageDurationInMillis));
    }

    private Duration getDuration(String line) {
        Matcher matcher = session.matcher(line);
        Duration sessionDuration = Duration.ZERO;
        if (matcher.find()) {
            LocalDateTime startSession = convertToDateTime(matcher.group(1));
            LocalDateTime endSession = convertToDateTime(matcher.group(2));
            if (startSession != null && endSession != null && startSession.isBefore(endSession)) {
                sessionDuration = Duration.between(startSession, endSession);
            }
        }
        return sessionDuration;
    }

    private String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        if (hours > 0 && minutes > 0) {
            return String.format("%dч %dм", hours, minutes);
        } else if (hours > 0) {
            return String.format("%dч", hours);
        } else {
            return String.format("%dм", minutes);
        }
    }

    private LocalDateTime convertToDateTime(String str) {
        LocalDateTime dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
        try {
            dateTime = LocalDateTime.parse(str, formatter);
            if (dateTime.isAfter(LocalDateTime.now()) || dateTime.isBefore(clubOpeningDate)) {
                throw new DateTimeException("Invalid dateTime");
            }
        } catch (DateTimeException e) {
            dateTime = null;
        }
        return dateTime;
    }

}
