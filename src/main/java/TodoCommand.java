public class TodoCommand extends Command{

    public static final String COMMAND_WORD = "todo";

    public static final String MESSAGE_SUCCESS = "Yes, Master. I've added this task to your list:\n%s" +
            "\nThere are %d tasks in your list now.";

    public static final int TASK_INDENT = 2;

    private final String taskName;

    public TodoCommand(String taskName) {
        super();
        this.taskName = taskName;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.addToDo(taskName);
        ui.printStandardFormat(String.format(MESSAGE_SUCCESS, task.toString().indent(TASK_INDENT), taskList.size()));
    }
}
