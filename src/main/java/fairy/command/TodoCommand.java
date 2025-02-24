package fairy.command;

import fairy.storage.Storage;
import fairy.task.Task;
import fairy.task.TaskList;

import fairy.common.Messages;
import fairy.ui.Ui;

public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    public static final int TASK_INDENT = 2;

    private final String taskName;

    public TodoCommand(String taskName) {
        super();
        this.taskName = taskName;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.addToDo(taskName);
        ui.printStandardFormat(String.format(Messages.MESSAGE_ADD_TASK_SUCCESS,
                task.toString().indent(TASK_INDENT), taskList.size()));
    }
}
