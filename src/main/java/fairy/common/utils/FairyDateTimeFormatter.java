package fairy.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FairyDateTimeFormatter {
    private static final String FORMAT_TIME_INPUT = "yyyyMMdd HHmm";
    private static final String FORMAT_TIME_OUTPUT = "dd LLL yyyy HH:mm";
    private static final String FORMAT_DATE_INPUT = "yyyyMMdd";

    private static final DateTimeFormatter FORMATTER_TIME_INPUT = DateTimeFormatter.ofPattern(FORMAT_TIME_INPUT);
    private static final DateTimeFormatter FORMATTER_TIME_OUTPUT = DateTimeFormatter.ofPattern(FORMAT_TIME_OUTPUT);
    private static final DateTimeFormatter FORMATTER_DATE_INPUT = DateTimeFormatter.ofPattern(FORMAT_DATE_INPUT);

    public static LocalDateTime parseDateTime(String date) {
        return LocalDateTime.parse(date, FORMATTER_TIME_INPUT);
    }

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, FORMATTER_DATE_INPUT);
    }

    public static String formatDateTimePrint(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER_TIME_OUTPUT);
    }

    public static String formatDateTimeFile(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER_TIME_INPUT);
    }
}
