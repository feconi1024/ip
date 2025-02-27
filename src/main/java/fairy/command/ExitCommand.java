package fairy.command;

import fairy.common.Messages;
import fairy.storage.Storage;
import fairy.task.TaskList;

/**
 * Represents a command of exiting from the application.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    public ExitCommand() {
        super();
    };

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return Messages.MESSAGE_EXIT;
    };

    @Override
    public boolean isExit() {
        return true;
    }
}
