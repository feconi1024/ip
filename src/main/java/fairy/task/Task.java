package fairy.task;

/**
 * Represents a task.
 */
public class Task {
    private final String taskName;
    private boolean done;

    /**
     * Constructs the task. Task is set to uncompleted when initialized.
     *
     * @param taskName Description of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        done = false;
    }

    public String getTaskName() {
        return taskName;
    }

    /**
     * Sets the task as completed.
     */
    public void setDo() {
        done = true;
    }

    /**
     * Sets the task as uncompleted.
     */
    public void setUndo() {
        done = false;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return Completion status of the task. {@code True} if the task is completed.
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Returns the string representation of the task for saving in file.
     *
     * @return String representation of the task for saving in file.
     */
    public String toFileString() {
        if (done) {
            return "T | " + taskName;
        } else {
            return "F | " + taskName;
        }
    }

    /**
     * Returns the string representation of the task for showing on user interface.
     *
     * @return String representation of the task for showing on UI.
     */
    public String toString() {
        if (done) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }
}
