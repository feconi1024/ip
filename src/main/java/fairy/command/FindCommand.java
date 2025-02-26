package fairy.command;

import java.util.Iterator;

import fairy.common.Messages;
import fairy.common.utils.FairyTaskListOutputFormatter;
import fairy.exception.EmptyListException;
import fairy.storage.Storage;
import fairy.task.Task;
import fairy.task.TaskList;
import fairy.ui.Ui;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    private final String keyword;

    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Iterator<Task> taskIterator = taskList.searchTaskByKeyword(keyword);
            ui.printStandardFormat(Messages.MESSAGE_LIST_INTRO
                    + FairyTaskListOutputFormatter.formatTaskList(taskIterator));
        } catch (EmptyListException e) {
            ui.printStandardFormat(Messages.MESSAGE_NO_TASKS_FOUND);
        }
    }
}
