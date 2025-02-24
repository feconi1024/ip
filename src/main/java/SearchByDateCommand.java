import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import java.util.Iterator;

public class SearchByDateCommand extends Command {

    public static final String COMMAND_WORD = "searchByDate";

    public final String date;

    public SearchByDateCommand(String date) {
        super();
        this.date = date;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Iterator<Task> taskIterator = taskList.searchTaskByDate(date);
            ui.printStandardFormat(Messages.MESSAGE_LIST_INTRO + FairyTaskListOutputFormatter.formatTaskList(taskIterator));
        } catch (DateTimeParseException e) {
            ui.printStandardFormat(Messages.MESSAGE_DATE_PARSE_EXCEPTION);
        } catch (DateTimeException e) {
            ui.printStandardFormat(String.format(Messages.MESSAGE_DATETIME_EXCEPTION, e.getMessage()));
        } catch (EmptyListException e) {
            ui.printStandardFormat(Messages.MESSAGE_NO_TASKS_FOUND);
        }
    }
}
