import java.util.Iterator;

public class FairyTaskListOutputFormatter {

    public static final String MESSAGE_NO_TASKS_FOUND = "No tasks found";

    public static String formatTaskList(Iterator<Task> tasks) throws EmptyListException {
        if (!tasks.hasNext()) {
            throw new EmptyListException(MESSAGE_NO_TASKS_FOUND);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; tasks.hasNext(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.next()).append("\n");
        }
        return sb.toString();
    }
}
