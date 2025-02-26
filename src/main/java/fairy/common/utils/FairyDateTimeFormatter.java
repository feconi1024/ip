package fairy.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility transforming time input with designated format into LocalDateTime instance, and vice versa.
 */
public class FairyDateTimeFormatter {

    /* Input format of date and time. */
    private static final String FORMAT_TIME_INPUT = "yyyyMMdd HHmm";

    /* Output format of date and time */
    private static final String FORMAT_TIME_OUTPUT = "dd LLL yyyy HH:mm";

    /* Input format of date only. */
    private static final String FORMAT_DATE_INPUT = "yyyyMMdd";

    private static final DateTimeFormatter FORMATTER_TIME_INPUT = DateTimeFormatter.ofPattern(FORMAT_TIME_INPUT);
    private static final DateTimeFormatter FORMATTER_TIME_OUTPUT = DateTimeFormatter.ofPattern(FORMAT_TIME_OUTPUT);
    private static final DateTimeFormatter FORMATTER_DATE_INPUT = DateTimeFormatter.ofPattern(FORMAT_DATE_INPUT);

    /**
     * Returns {@code LocalDateTime} instance transformed from date and time input.
     *
     * @param date Date and time input in format {@code YYYYMMDD HHMM }(e.g. {@code 20250101 0000}).
     * @return Object representing the same time as the input.
     */
    public static LocalDateTime parseDateTime(String date) {
        return LocalDateTime.parse(date, FORMATTER_TIME_INPUT);
    }

    /**
     * Returns {@code LocalDate} instance transformed from date input.
     *
     * @param date Date input in format {@code YYYYMMDD} (e.g. {@code 20250101}).
     * @return Object representing the same date as the input.
     */
    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, FORMATTER_DATE_INPUT);
    }

    /**
     * Returns formatted string output of date and time for UI showing.
     *
     * @param dateTime Date and time to be formatted.
     * @return Formatted date and time output in format {@code DD Mmm YYYY HH:MM} (e.g. {@code 01 Jan 2025 00:00}).
     */
    public static String formatDateTimePrint(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER_TIME_OUTPUT);
    }

    /**
     * Returns formatted string output of date and time for file storing.
     *
     * @param dateTime Date and time to be formatted.
     * @return Formatted date and time output in format {@code YYYYMMDD HHMM} (e.g. {@code 20250101 0000}).
     */
    public static String formatDateTimeFile(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER_TIME_INPUT);
    }
}
