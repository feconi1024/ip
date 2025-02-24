import java.time.DateTimeException;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";

    public static final String MESSAGE_SUCCESS  = "Yes, Master. I've added this task to your list:\n%s" +
            "\nThere are %d tasks in your list now.";
    public static final String MESSAGE_DATETIME_PARSE_EXCEPTION = "Date time exception: " +
            "Wrong format or illegal time. Correct format: YYYYMMDD hhmm";
    public static final String MESSAGE_DATETIME_EXCEPTION = "Date time exception: %s";

    public static final int TASK_INDENT = 2;

    private final String taskName;
    private final String startTime;
    private final String endTime;

    public EventCommand(String taskName, String startTime, String endTime) {
        super();
        this.taskName = taskName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            // Substitute with returned task later
            taskList.addEvent(taskName, startTime, endTime);
            Ui.printStandardFormat(String.format(MESSAGE_SUCCESS, taskName, taskList.size()));
        } catch (DateTimeParseException e) {
            Ui.printStandardFormat(MESSAGE_DATETIME_PARSE_EXCEPTION);
        } catch (DateTimeException e) {
            Ui.printStandardFormat(String.format(MESSAGE_DATETIME_EXCEPTION, e.getMessage()));
        }
    }
}
