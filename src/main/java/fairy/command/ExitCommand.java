package fairy.command;

import fairy.storage.Storage;
import fairy.task.TaskList;
import fairy.ui.Ui;

/**
 * Represents a command of exiting from the application.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    public ExitCommand() {
        super();
    };

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {};

    @Override
    public boolean isExit() {
        return true;
    }
}
