package fairy.command;

import fairy.storage.Storage;
import fairy.task.Task;
import fairy.task.TaskList;

/**
 * Represents a command of deleting task from the list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_SUCCESS = "Yes, Master. I've removed this task from your list:\n%s"
            + "\nThere are %d tasks in your list now.";
    public static final String MESSAGE_INDEX_OUT_OF_BOUNDS = "Index out of bounds: input exceeds the size of list: %d";

    /* Indentation of task information when being shown. */
    public static final int TASK_INDENT = 2;

    private final int taskIndex;

    /**
     * @param taskIndex Index of the task in the list to be deleted. Starts from 1.
     */
    public DeleteCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            Task task = tasks.deleteTask(taskIndex);
            return String.format(MESSAGE_SUCCESS, task.toString().indent(TASK_INDENT), tasks.size());
        } catch (IndexOutOfBoundsException e) {
            return String.format(MESSAGE_INDEX_OUT_OF_BOUNDS, taskIndex);
        }
    }
}
