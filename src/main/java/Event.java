import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public Event(String taskName, LocalDateTime startTime, LocalDateTime endTime) {
        super(taskName);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public String toFileString() {
        return "EVENT | " + super.toFileString() + " | " + FairyDateTimeFormatter.formatDateTimeFile(getStartTime())
                + " | " + FairyDateTimeFormatter.formatDateTimeFile(getEndTime());
    }

    public String toString() {
        return "[D]" + super.toString() + " (from: " + FairyDateTimeFormatter.formatDateTimePrint(getStartTime())
                + ", to: " + FairyDateTimeFormatter.formatDateTimePrint(getEndTime()) + ")";
    }
}
