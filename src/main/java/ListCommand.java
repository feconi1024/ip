public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_LIST_INTRO = "Tasks found are listed as follows:\n";
    public static final String MESSAGE_NO_TASKS_FOUND = "No tasks found.";

    public ListCommand() {
        super();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ui.printStandardFormat(MESSAGE_LIST_INTRO + FairyTaskListOutputFormatter.formatTaskList(taskList.iterator()));
        } catch (EmptyListException e) {
            ui.printStandardFormat(MESSAGE_NO_TASKS_FOUND);
        }
    }
}
