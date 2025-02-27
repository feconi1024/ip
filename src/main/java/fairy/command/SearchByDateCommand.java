package fairy.command;

import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import java.util.Iterator;

import fairy.common.Messages;
import fairy.common.utils.FairyTaskListOutputFormatter;
import fairy.exception.EmptyListException;
import fairy.storage.Storage;
import fairy.task.Task;
import fairy.task.TaskList;

/**
 * Represents a command of searching tasks in list with given date information.
 */
public class SearchByDateCommand extends Command {

    public static final String COMMAND_WORD = "searchByDate";

    private final String date;

    public SearchByDateCommand(String date) {
        super();
        this.date = date;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            Iterator<Task> taskIterator = tasks.searchTaskByDate(date);
            return Messages.MESSAGE_LIST_INTRO
                    + FairyTaskListOutputFormatter.formatTaskList(taskIterator);
        } catch (DateTimeParseException e) {
            return Messages.MESSAGE_DATE_PARSE_EXCEPTION;
        } catch (DateTimeException e) {
            return String.format(Messages.MESSAGE_DATETIME_EXCEPTION, e.getMessage());
        } catch (EmptyListException e) {
            return Messages.MESSAGE_NO_TASKS_FOUND;
        }
    }
}
