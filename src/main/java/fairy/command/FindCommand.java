package fairy.command;

import java.util.Iterator;

import fairy.common.Messages;
import fairy.common.utils.FairyTaskListOutputFormatter;
import fairy.exception.EmptyListException;
import fairy.storage.Storage;
import fairy.task.Task;
import fairy.task.TaskList;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    private final String keyword;

    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            Iterator<Task> taskIterator = tasks.searchTaskByKeyword(keyword);
            return Messages.MESSAGE_LIST_INTRO
                    + FairyTaskListOutputFormatter.formatTaskList(taskIterator);
        } catch (EmptyListException e) {
            return Messages.MESSAGE_NO_TASKS_FOUND;
        }
    }
}
