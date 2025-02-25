package fairy.task;

import java.time.LocalDateTime;

import fairy.common.utils.FairyDateTimeFormatter;

/**
 * Represents an event task.
 * Event is a kind of task that has start time and end time.
 */
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
        return "[E]" + super.toString() + " (from: " + FairyDateTimeFormatter.formatDateTimePrint(getStartTime())
                + ", to: " + FairyDateTimeFormatter.formatDateTimePrint(getEndTime()) + ")";
    }
}
