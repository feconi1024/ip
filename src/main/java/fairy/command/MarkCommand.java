package fairy.command;

import fairy.storage.Storage;
import fairy.task.Task;
import fairy.task.TaskList;

/**
 * Represents a command of marking a task from list as completed.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_SUCCESS = "Nice job, Master. I've marked this task as done: \n%s";
    public static final String MESSAGE_INDEX_OUT_OF_BOUNDS = "Index out of bounds: input exceeds the size of list: %d";

    /* Indentation of task information when being shown. */
    public static final int TASK_INDENT = 2;

    private final int taskIndex;

    /**
     * @param taskIndex Index of the task in the list to be marked as completed. Starts from 1.
     */
    public MarkCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            Task task = tasks.markTask(taskIndex);
            return String.format(MESSAGE_SUCCESS, task.toString().indent(TASK_INDENT));
        } catch (IndexOutOfBoundsException e) {
            return String.format(MESSAGE_INDEX_OUT_OF_BOUNDS, tasks.size());
        }
    }
}
