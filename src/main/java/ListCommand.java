public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public ListCommand() {
        super();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ui.printStandardFormat(Messages.MESSAGE_LIST_INTRO + FairyTaskListOutputFormatter.formatTaskList(taskList.iterator()));
        } catch (EmptyListException e) {
            ui.printStandardFormat(Messages.MESSAGE_NO_TASKS_FOUND);
        }
    }
}
