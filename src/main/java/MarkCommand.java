public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_SUCCESS = "Nice job, Master. I've marked this task as done: \n%s";
    public static final String MESSAGE_INDEX_OUT_OF_BOUNDS = "Index out of bounds: input exceeds the size of list: %d";

    public static final int TASK_INDENT = 2;

    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = taskList.markTask(taskIndex);
            ui.printStandardFormat(String.format(MESSAGE_SUCCESS, task.toString().indent(TASK_INDENT)));
        } catch (IndexOutOfBoundsException e) {
            ui.printStandardFormat(String.format(MESSAGE_INDEX_OUT_OF_BOUNDS, taskList.size()));
        }
    }
}
