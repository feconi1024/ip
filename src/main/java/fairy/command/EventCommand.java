package fairy.command;

import java.time.DateTimeException;
import java.time.format.DateTimeParseException;

import fairy.common.Messages;
import fairy.storage.Storage;
import fairy.task.Task;
import fairy.task.TaskList;

/**
 * Represents a command of adding an event to the list of tasks.
 */
public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";

    /* Indentation of task information when being shown. */
    public static final int TASK_INDENT = 2;

    private final String taskName;
    private final String startTime;
    private final String endTime;

    public EventCommand(String taskName, String startTime, String endTime) {
        super();
        this.taskName = taskName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            Task task = tasks.addEvent(taskName, startTime, endTime);
            return String.format(Messages.MESSAGE_ADD_TASK_SUCCESS,
                    task.toString().indent(TASK_INDENT), tasks.size());
        } catch (DateTimeParseException e) {
            return Messages.MESSAGE_DATETIME_PARSE_EXCEPTION;
        } catch (DateTimeException e) {
            return String.format(Messages.MESSAGE_DATETIME_EXCEPTION, e.getMessage());
        }
    }
}
