package fairy.common.utils;

import java.util.Iterator;

import fairy.exception.EmptyListException;
import fairy.task.Task;

public class FairyTaskListOutputFormatter {

    public static String formatTaskList(Iterator<Task> tasks) throws EmptyListException {
        if (!tasks.hasNext()) {
            // list empty
            throw new EmptyListException();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; tasks.hasNext(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.next()).append("\n");
        }
        return sb.toString();
    }
}
