package fairy.task;

public class Task {
    private final String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setDo() {
        isDone = true;
    }

    public void setUndo() {
        isDone = false;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String toFileString() {
        if (isDone) {
            return "T | " + taskName;
        } else {
            return "F | " + taskName;
        }
    }

    public String toString() {
        if (isDone) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }
}
