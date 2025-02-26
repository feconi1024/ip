package fairy.command;

import fairy.storage.Storage;
import fairy.task.TaskList;
import fairy.ui.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {

    public Command() {};

    /**
     * Executes the command and shows the result on UI.
     *
     * @param tasks List of tasks.
     * @param ui User interface to interact with.
     * @param storage File storing task records.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns indication of exit command.
     *
     * @return Indication of the command. {@code True} means exit command.
     */
    public boolean isExit() {
        return false;
    }
}
