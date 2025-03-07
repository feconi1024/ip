package fairy.command;

import fairy.storage.Storage;
import fairy.task.Task;
import fairy.task.TaskList;

/**
 * Represents a command of marking task from list as incomplete.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_SUCCESS = "OK, Master. I've marked this task as not done yet: \n%s";
    public static final String MESSAGE_INDEX_OUT_OF_BOUNDS = "Index out of bounds: input exceeds the size of list: %d";

    /* Indentation of task information when being shown. */
    public static final int TASK_INDENT = 2;

    private final int taskIndex;

    /**
     * @param taskIndex Index of the task in the list to be marked as incomplete. Starts from 1.
     */
    public UnmarkCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            Task task = tasks.unmarkTask(taskIndex);
            return String.format(MESSAGE_SUCCESS, task.toString().indent(TASK_INDENT));
        } catch (IndexOutOfBoundsException e) {
            return String.format(MESSAGE_INDEX_OUT_OF_BOUNDS, tasks.size());
        }
    }
}
