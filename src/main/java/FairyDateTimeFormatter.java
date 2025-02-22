import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FairyDateTimeFormatter {
    private static final String FORMAT_TIME_INPUT = "yyyyMMdd HHmm";
    private static final String FORMAT_TIME_OUTPUT = "dd LLL yyyy HH:mm";

    private static final DateTimeFormatter FORMATTER_TIME_INPUT = DateTimeFormatter.ofPattern(FORMAT_TIME_INPUT);
    private static final DateTimeFormatter FORMATTER_TIME_OUTPUT = DateTimeFormatter.ofPattern(FORMAT_TIME_OUTPUT);

    public static LocalDateTime parseDateTime(String date) {
        return LocalDateTime.parse(date, FORMATTER_TIME_INPUT);
    }

    public static String formatDateTimePrint(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER_TIME_OUTPUT);
    }

    public static String formatDateTimeFile(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER_TIME_INPUT);
    }
}
