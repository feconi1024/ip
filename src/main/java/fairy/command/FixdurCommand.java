package fairy.command;

import fairy.common.Messages;
import fairy.storage.Storage;
import fairy.task.Task;
import fairy.task.TaskList;

/**
 * Represents a command of adding an fixed duration task to the list of tasks.
 */
public class FixdurCommand extends Command {

    public static final String COMMAND_WORD = "fixdur";

    public static final String MESSAGE_ARITHMETIC_EXCEPTION = "Arithmetic exception: Input too big or illegal.";

    /* Indentation of task information when being shown. */
    public static final int TASK_INDENT = 2;

    private final String taskName;
    private final long duration;

    public FixdurCommand(String taskName, long duration) {
        this.taskName = taskName;
        this.duration = duration;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            Task task = tasks.addFixedDuration(taskName, duration);
            return String.format(Messages.MESSAGE_ADD_TASK_SUCCESS,
                    task.toString().indent(TASK_INDENT), tasks.size());
        } catch (ArithmeticException e) {
            return MESSAGE_ARITHMETIC_EXCEPTION;
        }
    }
}
