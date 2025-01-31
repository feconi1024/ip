public class Task {
    private final String taskName;
    private boolean done;

    public Task(String taskName) {
        this.taskName = taskName;
        done = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setDo() {
        done = true;
    }

    public void setUndo() {
        done = false;
    }

    public boolean isDone() {
        return done;
    }

    public String toString() {
        if (done) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }
}
