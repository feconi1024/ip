package fairy.command;

import fairy.storage.Storage;
import fairy.task.TaskList;
import fairy.ui.Ui;

public abstract class Command {

    public Command() {};

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
