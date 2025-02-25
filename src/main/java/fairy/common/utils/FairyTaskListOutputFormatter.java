package fairy.common.utils;

import java.util.Iterator;

import fairy.exception.EmptyListException;
import fairy.task.Task;

/**
 * Utility transforming list of tasks into a single string for output.
 */
public class FairyTaskListOutputFormatter {

    /**
     * Returns the string representing all tasks in list that can be shown by user interface.
     *
     * @param tasks List of tasks to be formatted into output string.
     * @return UI-style representation of the list of tasks.
     * @throws EmptyListException If the list of tasks given is empty.
     */
    public static String formatTaskList(Iterator<Task> tasks) throws EmptyListException {
        if (!tasks.hasNext()) {
            throw new EmptyListException();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; tasks.hasNext(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.next()).append("\n");
        }
        return sb.toString();
    }
}
