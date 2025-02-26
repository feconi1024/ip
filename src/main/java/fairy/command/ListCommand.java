package fairy.command;

import fairy.common.utils.FairyTaskListOutputFormatter;
import fairy.exception.EmptyListException;

import fairy.common.Messages;
import fairy.storage.Storage;
import fairy.task.TaskList;
import fairy.ui.Ui;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public ListCommand() {
        super();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ui.showStandardFormat(Messages.MESSAGE_LIST_INTRO + FairyTaskListOutputFormatter.formatTaskList(taskList.iterator()));
        } catch (EmptyListException e) {
            ui.showStandardFormat(Messages.MESSAGE_NO_TASKS_FOUND);
        }
    }
}
