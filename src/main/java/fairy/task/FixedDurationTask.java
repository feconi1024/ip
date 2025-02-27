package fairy.task;

import java.time.Duration;

/**
 * Represents a task with fixed duration.
 */
public class FixedDurationTask extends Task {

    private Duration duration;

    public FixedDurationTask(String taskName, Duration duration) {
        super(taskName);
        this.duration = duration;
    }

    public Duration getDuration() {
        return duration;
    }

    @Override
    public String toFileString() {
        return "FIXEDDUR | " + super.toFileString() + " | " + duration.toHours();
    }

    @Override
    public String toString() {
        return "[F]" + super.toString() + " (duration: " + duration.toHours() + " hours)";
    }
}
