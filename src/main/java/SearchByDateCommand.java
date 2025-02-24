import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import java.util.Iterator;

public class SearchByDateCommand extends Command {

    public static final String COMMAND_WORD = "searchByDate";

    public static final String MESSAGE_LIST_INTRO = "Tasks found are listed as follows:\n";
    public static final String MESSAGE_NO_TASKS_FOUND = "No tasks found.";
    public static final String MESSAGE_DATETIME_PARSE_EXCEPTION = "Date time exception: " +
            "Wrong format or illegal time. Correct format: YYYYMMDD";
    public static final String MESSAGE_DATETIME_EXCEPTION = "Date time exception: %s";

    public final String date;

    public SearchByDateCommand(String date) {
        super();
        this.date = date;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Iterator<Task> taskIterator = taskList.searchTaskByDate(date);
            ui.printStandardFormat(MESSAGE_LIST_INTRO + FairyTaskListOutputFormatter.formatTaskList(taskIterator));
        } catch (DateTimeParseException e) {
            ui.printStandardFormat(MESSAGE_DATETIME_PARSE_EXCEPTION);
        } catch (DateTimeException e) {
            ui.printStandardFormat(String.format(MESSAGE_DATETIME_EXCEPTION, e.getMessage()));
        } catch (EmptyListException e) {
            ui.printStandardFormat(MESSAGE_NO_TASKS_FOUND);
        }
    }
}
