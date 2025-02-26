package fairy.task;

/**
 * Represents a task.
 */
public class Task {
    private final String taskName;
    private boolean isDone;

    /**
     * Constructs the task. Task is set to uncompleted when initialized.
     *
     * @param taskName Description of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getTaskName() {
        return taskName;
    }

    /**
     * Sets the task as completed.
     */
    public void setDo() {
        isDone = true;
    }

    /**
     * Sets the task as uncompleted.
     */
    public void setUndo() {
        isDone = false;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return Completion status of the task. {@code True} if the task is completed.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns the string representation of the task for saving in file.
     *
     * @return String representation of the task for saving in file.
     */
    public String toFileString() {
        if (isDone) {
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
        if (isDone) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }
}
