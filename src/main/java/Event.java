public class Event extends Task {
    private final String startTime;
    private final String endTime;

    public Event(String taskName, String startTime, String endTime) {
        super(taskName);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    @Override
    public String toFileString() {
        return "EVENT | " + super.toFileString() + " | " + startTime + " | " + endTime;
    }

    public String toString() {
        return "[D]" + super.toString() + " (from: " + getStartTime() + ", to: " + getEndTime() + ")";
    }
}
