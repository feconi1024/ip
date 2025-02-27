package fairy.command;

import fairy.common.Messages;
import fairy.storage.Storage;
import fairy.task.Task;
import fairy.task.TaskList;

/**
 * Represents a command of adding a todo to the list of tasks.
 */
public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    /* Indentation of task information when being shown. */
    public static final int TASK_INDENT = 2;

    private final String taskName;

    public TodoCommand(String taskName) {
        super();
        this.taskName = taskName;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task task = tasks.addToDo(taskName);
        return String.format(Messages.MESSAGE_ADD_TASK_SUCCESS,
                task.toString().indent(TASK_INDENT), tasks.size());
    }
}
