package fairy.command;

import java.time.DateTimeException;
import java.time.format.DateTimeParseException;

import fairy.common.Messages;
import fairy.storage.Storage;
import fairy.task.Task;
import fairy.task.TaskList;

/**
 * Represents a command of adding a deadline to the list of tasks.
 */
public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";

    /* Indentation of task information when being shown. */
    public static final int TASK_INDENT = 2;

    private final String taskName;
    private final String endTime;

    public DeadlineCommand(String taskName, String endTime) {
        super();
        this.taskName = taskName;
        this.endTime = endTime;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            Task task = tasks.addDeadline(taskName, endTime);
            return String.format(Messages.MESSAGE_ADD_TASK_SUCCESS,
                    task.toString().indent(TASK_INDENT), tasks.size());
        } catch (DateTimeParseException e) {
            return Messages.MESSAGE_DATETIME_PARSE_EXCEPTION;
        } catch (DateTimeException e) {
            return String.format(Messages.MESSAGE_DATETIME_EXCEPTION, e.getMessage());
        }
    }
}
