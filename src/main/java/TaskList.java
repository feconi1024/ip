import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

public class TaskList {

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

    public Task getTask(int index) throws IndexOutOfBoundsException {
        return this.tasks.get(index);
    }

    public Iterator<Task> iterator() {
        return this.tasks.iterator();
    }

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
                default:
                    return 0;
            }
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    public void markTask(int index) throws IndexOutOfBoundsException {
        tasks.get(index - 1).setDo();
    }

    public void unmarkTask(int index) throws IndexOutOfBoundsException {
        tasks.get(index - 1).setUndo();
    }

    public Todo addToDo(String task) {
        Todo newTask = new Todo(task);
        tasks.add(newTask);
        return newTask;
    }

    public void addToDoFromRecord(String task, String done) {
        Todo newTask = new Todo(task);
        if (done.equals("T")) {
            newTask.setDo();
        } else {
            newTask.setUndo();
        }
        tasks.add(newTask);
    }

    public Deadline addDeadline(String task, String endTime) {
        Deadline newTask = new Deadline(task, FairyDateTimeFormatter.parseDateTime(endTime));
        tasks.add(newTask);
        return newTask;
    }

    public void addDeadlineFromRecord(String task, String endTime, String done) {
        Deadline newTask = new Deadline(task, FairyDateTimeFormatter.parseDateTime(endTime));
        if (done.equals("T")) {
            newTask.setDo();
        } else {
            newTask.setUndo();
        }
        tasks.add(newTask);
    }

    public Event addEvent(String task, String startTime, String endTime) throws DateTimeException {
        LocalDateTime start = FairyDateTimeFormatter.parseDateTime(startTime);
        LocalDateTime end = FairyDateTimeFormatter.parseDateTime(endTime);
        // start should be no later than end
        if (end.isBefore(start)) {
            throw new DateTimeException("Start time is after end time");
        }
        Event newTask = new Event(task, start, end);
        tasks.add(newTask);
        return newTask;
    }

    public void addEventFromRecord(String task, String startTime, String endTime, String done)
            throws DateTimeException {
        LocalDateTime start = FairyDateTimeFormatter.parseDateTime(startTime);
        LocalDateTime end = FairyDateTimeFormatter.parseDateTime(endTime);
        // start should be no later than end
        if (end.isBefore(start)) {
            throw new DateTimeException("Start time is after end time.");
        }
        Event newTask = new Event(task, start, end);
        if (done.equals("T")) {
            newTask.setDo();
        } else {
            newTask.setUndo();
        }
        tasks.add(newTask);
    }

    public Task deleteTask(int index) throws IndexOutOfBoundsException {
        if (index > tasks.size()) {
            throw new IndexOutOfBoundsException("input " + index + " exceeds the size of list: " + tasks.size());
        }
        Task removedTask = tasks.remove(index - 1);
        return removedTask;
    }

    public Iterator<Task> searchTaskByDate(String date) {
        LocalDate d = FairyDateTimeFormatter.parseDate(date);
        ArrayList<Task> foundTasks = new ArrayList<>();

        for (Task task : this.tasks) {
            if (task instanceof Deadline && ((Deadline) task).getEndTime().toLocalDate().equals(d)) {
                foundTasks.add(task);
            } else if (task instanceof Event && !((d.isBefore(((Event) task).getStartTime().toLocalDate())) ||
                    d.isAfter(((Event) task).getEndTime().toLocalDate()))) {
                foundTasks.add(task);
            }
        }

        return foundTasks.iterator();
    }

}
