package fairy.command;

import fairy.common.Messages;
import fairy.common.utils.FairyTaskListOutputFormatter;
import fairy.exception.EmptyListException;
import fairy.storage.Storage;
import fairy.task.TaskList;

/**
 * Represents a command of showing all tasks in the list to the output.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public ListCommand() {
        super();
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            return Messages.MESSAGE_LIST_INTRO
                    + FairyTaskListOutputFormatter.formatTaskList(tasks.iterator());
        } catch (EmptyListException e) {
            return Messages.MESSAGE_NO_TASKS_FOUND;
        }
    }
}
