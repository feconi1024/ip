package fairy.command;

import fairy.storage.Storage;
import fairy.task.Task;
import fairy.task.TaskList;
import fairy.ui.Ui;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_SUCCESS = "Yes, Master. I've removed this task from your list:\n%s"
            + "\nThere are %d tasks in your list now.";
    public static final String MESSAGE_INDEX_OUT_OF_BOUNDS = "Index out of bounds: input exceeds the size of list: %d";

    public static final int TASK_INDENT = 2;

    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = taskList.deleteTask(taskIndex);
            ui.showStandardFormat(String.format(MESSAGE_SUCCESS, task.toString().indent(TASK_INDENT), taskList.size()));
        } catch (IndexOutOfBoundsException e) {
            ui.showStandardFormat(String.format(MESSAGE_INDEX_OUT_OF_BOUNDS, taskIndex));
        }
    }
}
