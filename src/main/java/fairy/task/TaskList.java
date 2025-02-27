package fairy.task;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import fairy.common.utils.FairyDateTimeFormatter;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    public static final String MESSAGE_DATETIME_START_AFTER_END_ERROR = "Start time is after end time";

    public static final String REGEX_KEYWORD_MATCH = ".*";

    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int size() {
        return this.tasks.size();
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Returns the iterator for iteration of task in list.
     *
     * @return Iterator of the list of tasks.
     */
    public Iterator<Task> iterator() {
        return this.tasks.iterator();
    }

    /**
     * Adds task to list from record string given.
     * This method contains a parser for record strings.
     *
     * @param record String representing the task from file storing tasks.
     * @return Flag indicating the result of the process. 1 means a success. 0 means a failure.
     */
    public int addTaskFromRecord(String record) {
        String[] args = record.split(" \\| ");
        try {
            switch (args[0]) {
            case "TODO":
                addToDoFromRecord(args[2], args[1]);
                break;
            case "DEADLINE":
                addDeadlineFromRecord(args[2], args[3], args[1]);
                break;
            case "EVENT":
                addEventFromRecord(args[2], args[3], args[4], args[1]);
                break;
            case "FIXDUR":
                addFixedDurationFromRecord(args[2], args[3], args[1]);
                break;
            default:
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    /**
     * Marks a task as completed.
     *
     * @param index The index of the task in the list. Starts from 1.
     * @return The task set to complete.
     * @throws IndexOutOfBoundsException If the index is smaller than 1 or exceeds size of the list.
     */
    public Task markTask(int index) throws IndexOutOfBoundsException {
        tasks.get(index - 1).setDo();
        return tasks.get(index - 1);
    }

    /**
     * Marks a task as uncompleted.
     *
     * @param index The index of the task in the list. Starts from 1.
     * @return The task set to uncompleted.
     * @throws IndexOutOfBoundsException If the index is smaller than 1 or exceeds size of the list.
     */
    public Task unmarkTask(int index) throws IndexOutOfBoundsException {
        tasks.get(index - 1).setUndo();
        return tasks.get(index - 1);
    }

    /**
     * Adds a todo task to the list.
     *
     * @param task Description of the task.
     * @return The task added to the list.
     */
    public Todo addToDo(String task) {
        Todo newTask = new Todo(task);
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Adds a todo task to the list using information from file storage.
     * This method will not produce UI output.
     *
     * @param task Description of the task.
     * @param done Completion status of the task. {@code "T"} represents completed. {@code "F"} represents uncompleted.
     */
    public void addToDoFromRecord(String task, String done) {
        Todo newTask = new Todo(task);

        assert done.equals("T") || done.equals("F");

        if (done.equals("T")) {
            newTask.setDo();
        } else {
            newTask.setUndo();
        }
        tasks.add(newTask);
    }

    /**
     * Adds a deadline task to the list.
     *
     * @param task Description of the task.
     * @param endTime End time of the task.
     * @return The task added to the list.
     */
    public Deadline addDeadline(String task, String endTime) {
        Deadline newTask = new Deadline(task, FairyDateTimeFormatter.parseDateTime(endTime));
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Adds a deadline task to the list using information from file storage.
     * This method will not produce UI output.
     *
     * @param task Description of the task.
     * @param endTime End time of the task.
     * @param done Completion status of the task. {@code "T"} represents completed. {@code "F"} represents uncompleted.
     */
    public void addDeadlineFromRecord(String task, String endTime, String done) {
        Deadline newTask = new Deadline(task, FairyDateTimeFormatter.parseDateTime(endTime));

        assert done.equals("T") || done.equals("F");

        if (done.equals("T")) {
            newTask.setDo();
        } else {
            newTask.setUndo();
        }
        tasks.add(newTask);
    }

    /**
     * Adds an event task to the list.
     *
     * @param task Description of the task.
     * @param startTime Start time of the task.
     * @param endTime End time of the task.
     * @return The task added to the list.
     * @throws DateTimeException If the start time is after the end time.
     */
    public Event addEvent(String task, String startTime, String endTime) throws DateTimeException {
        LocalDateTime start = FairyDateTimeFormatter.parseDateTime(startTime);
        LocalDateTime end = FairyDateTimeFormatter.parseDateTime(endTime);

        // start should be no later than end
        if (end.isBefore(start)) {
            throw new DateTimeException(MESSAGE_DATETIME_START_AFTER_END_ERROR);
        }

        Event newTask = new Event(task, start, end);
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Adds an event task to the list using information from file storage.
     * This method will not produce UI output.
     *
     * @param task Description of the task.
     * @param startTime Start time of the task.
     * @param endTime End time of the task.
     * @param done Completion status of the task. {@code "T"} represents completed. {@code "F"} represents uncompleted.
     * @throws DateTimeException If the start time is after the end time.
     */
    public void addEventFromRecord(String task, String startTime, String endTime, String done)
            throws DateTimeException {
        LocalDateTime start = FairyDateTimeFormatter.parseDateTime(startTime);
        LocalDateTime end = FairyDateTimeFormatter.parseDateTime(endTime);

        // start should be no later than end
        if (end.isBefore(start)) {
            throw new DateTimeException(MESSAGE_DATETIME_START_AFTER_END_ERROR);
        }
        Event newTask = new Event(task, start, end);

        assert done.equals("T") || done.equals("F");

        if (done.equals("T")) {
            newTask.setDo();
        } else {
            newTask.setUndo();
        }
        tasks.add(newTask);
    }

    /**
     * Adds a fixed duration task to the list.
     *
     * @param task Description of the task.
     * @param duration Duration of the task in hours.
     * @return The task added to the list.
     */
    public FixedDurationTask addFixedDuration(String task, long duration) throws IllegalArgumentException {
        if (duration <= 0) {
            // duration must be positive
            throw new IllegalArgumentException();
        }

        FixedDurationTask newTask = new FixedDurationTask(task, Duration.ofHours(duration));
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Adds an fixed duration task to the list using information from file storage.
     * This method will not produce UI output.
     *
     * @param task Description of the task.
     * @param duration Duration of the task in hours.
     * @param done The task added to the list.
     */
    public void addFixedDurationFromRecord(String task, String duration, String done) {
        if (Long.parseLong(duration) <= 0) {
            // duration must be positive
            throw new IllegalArgumentException();
        }

        FixedDurationTask newTask = new FixedDurationTask(task, Duration.ofHours(Long.parseLong(duration)));

        assert done.equals("T") || done.equals("F");

        if (done.equals("T")) {
            newTask.setDo();
        } else {
            newTask.setUndo();
        }
        tasks.add(newTask);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index The index of the task in the list. Starts from 1.
     * @return The task removed from the list.
     * @throws IndexOutOfBoundsException If the index is smaller than 1 or exceeds size of the list.
     */
    public Task deleteTask(int index) throws IndexOutOfBoundsException {
        return tasks.remove(index - 1);
    }

    /**
     * Searches tasks by the given keyword.
     * Case-insensitive. Match any substring in task descriptions that is the same as the keyword.
     *
     * @param keyword Word used for searching.
     * @return Iterator of a list of tasks with description containing the keyword.
     */
    public Iterator<Task> searchTaskByKeyword(String keyword) {
        assert keyword != null && !keyword.isEmpty();

        ArrayList<Task> foundTasks = new ArrayList<>();

        for (Task task : tasks) {
            String taskNameLower = task.getTaskName().toLowerCase();
            String keywordRegexLower = REGEX_KEYWORD_MATCH + keyword.toLowerCase() + REGEX_KEYWORD_MATCH;
            if (taskNameLower.matches(keywordRegexLower)) {
                foundTasks.add(task);
            }
        }

        return foundTasks.iterator();
    }

    /**
     * Searches tasks by the given date.
     * Returns deadlines that ends at the date given, or events happening at the date given.
     *
     * @param date Date information used for searching.
     * @return Iterator of a list of tasks satisfying the date requirement.
     */
    public Iterator<Task> searchTaskByDate(String date) {
        LocalDate d = FairyDateTimeFormatter.parseDate(date);
        ArrayList<Task> foundTasks = new ArrayList<>();

        for (Task task : tasks) {
            boolean isDeadline = task instanceof Deadline;
            boolean isEvent = task instanceof Event;
            boolean isOnDeadline = false;
            boolean isBeforeEvent = true;
            boolean isAfterEvent = true;

            // whether event is on the date
            if (isDeadline) {
                isOnDeadline = ((Deadline) task).getEndTime().toLocalDate().equals(d);
            } else if (isEvent) {
                isBeforeEvent = d.isBefore(((Event) task).getStartTime().toLocalDate());
                isAfterEvent = d.isAfter(((Event) task).getEndTime().toLocalDate());
            }

            if (isOnDeadline || !(isBeforeEvent || isAfterEvent)) {
                foundTasks.add(task);
            }
        }

        return foundTasks.iterator();
    }

}
