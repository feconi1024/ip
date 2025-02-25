package fairy.task;

import java.time.LocalDateTime;

import fairy.common.utils.FairyDateTimeFormatter;

/**
 * Represents a deadline task.
 * Deadline task is a kind of task that has end time.
 */
public class Deadline extends Task {
    private final LocalDateTime endTime;

    public Deadline(String taskName, LocalDateTime endTime){
        super(taskName);
        this.endTime = endTime;
    }

    public LocalDateTime getEndTime(){
        return endTime;
    }

    @Override
    public String toFileString() {
        return "DEADLINE | " + super.toFileString() + " | " + FairyDateTimeFormatter.formatDateTimeFile(getEndTime());
    }

    public String toString(){
        return "[D]" + super.toString() + " (by: " + FairyDateTimeFormatter.formatDateTimePrint(getEndTime()) + ")";
    }
}
