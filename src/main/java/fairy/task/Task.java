package fairy.task;

/**
 * Represents a task.
 */
public class Task {
    private final String taskName;
    private boolean isDone = false;

    /**
     * Constructs the task. Task is set to uncompleted when initialized.
     *
     * @param taskName Description of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
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
