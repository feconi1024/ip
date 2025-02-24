import java.time.DateTimeException;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";

    public static final int TASK_INDENT = 2;

    private final String taskName;
    private final String endTime;

    public DeadlineCommand(String taskName, String endTime) {
        super();
        this.taskName = taskName;
        this.endTime = endTime;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = taskList.addDeadline(taskName, endTime);
            ui.printStandardFormat(String.format(Messages.MESSAGE_ADD_TASK_SUCCESS,
                    task.toString().indent(TASK_INDENT), taskList.size()));
        } catch (DateTimeParseException e) {
            ui.printStandardFormat(Messages.MESSAGE_DATETIME_PARSE_EXCEPTION);
        } catch (DateTimeException e) {
            ui.printStandardFormat(String.format(Messages.MESSAGE_DATETIME_EXCEPTION, e.getMessage()));
        }
    }
}
