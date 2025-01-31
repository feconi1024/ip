public class Deadline extends Task {
    private final String endTime;

    public Deadline(String taskName, String endTime){
        super(taskName);
        this.endTime = endTime;
    }

    public String getEndTime(){
        return endTime;
    }

    public String toString(){
        return "[D]" + super.toString() + " (by: " + getEndTime() + ")";
    }
}
