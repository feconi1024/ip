import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Fairy {
    public static final ArrayList<Task> TASKS = new ArrayList<>();
    private static final String FILE = "./data/fairytasks.txt";
    private static final String DIR = "./data/";

    private static List<String> parseCommand(String input) {
        List<String> result = new ArrayList<>();
        if (input == null || input.isEmpty()) {
            return result;
        }

        // Split into command and the rest of the string
        String[] cmdSplit = input.split(" ", 2);
        String command = cmdSplit[0];
        result.add(command);

        if (cmdSplit.length > 1) {
            String rest = cmdSplit[1];
            // Split the rest on any occurrence of whitespace followed by /word
            String[] parts = rest.split("\\s+/\\w+");
            for (String part : parts) {
                String trimmedPart = part.trim();
                if (!trimmedPart.isEmpty()) {
                    result.add(trimmedPart);
                }
            }
        }

        return result;
    }

    private static void addTask(String task) {
        TASKS.add(new Task(task));
        Ui.printStandardFormat("Added: " + task);
    }

    public static int addTaskFromRecord(String record) {
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

    private static void markTask(int index) throws IndexOutOfBoundsException {
        if (index > TASKS.size()) {
            throw new IndexOutOfBoundsException("input " + index + " exceeds the size of list: " + TASKS.size());
        }
        TASKS.get(index - 1).setDo();
        Ui.printStandardFormat("Nice job, Master. I've marked this task as done: \n" +
                TASKS.get(index - 1).toString().indent(2));
    }

    private static void unmarkTask(int index) throws IndexOutOfBoundsException {
        if (index > TASKS.size()) {
            throw new IndexOutOfBoundsException("input " + index + " exceeds the size of list: " + TASKS.size());
        }
        TASKS.get(index - 1).setUndo();
        Ui.printStandardFormat("OK, Master. I've marked this task as not done yet: \n" +
                TASKS.get(index - 1).toString().indent(2));
    }

    private static void printTaskList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            Ui.printStandardFormat("No tasks found.");
            return;
        }
        String output = "Tasks found are listed as follows:\n";
        for (int i = 0; i < tasks.size(); i++) {
            output += (i + 1) + ". " + tasks.get(i) + "\n";
        }
        Ui.printStandardFormat(output);
    }

    private static void addToDo(String task) {
        Todo newTask = new Todo(task);
        TASKS.add(newTask);
        Ui.printStandardFormat("Yes, Master. I've added this task to your list:\n" + newTask.toString().indent(2) +
                "\nThere are " + TASKS.size() + " tasks in your list now.");
    }

    private static void addToDoFromRecord(String task, String done) {
        Todo newTask = new Todo(task);
        if (done.equals("T")) {
            newTask.setDo();
        } else {
            newTask.setUndo();
        }
        TASKS.add(newTask);
    }

    private static void addDeadline(String task, String endTime) {
        Deadline newTask = new Deadline(task, FairyDateTimeFormatter.parseDateTime(endTime));
        TASKS.add(newTask);
        Ui.printStandardFormat("Yes, Master. I've added this task to your list:\n" + newTask.toString().indent(2) +
                "\nThere are " + TASKS.size() + " tasks in your list now.");
    }

    private static void addDeadlineFromRecord(String task, String endTime, String done) {
        Deadline newTask = new Deadline(task, FairyDateTimeFormatter.parseDateTime(endTime));
        if (done.equals("T")) {
            newTask.setDo();
        } else {
            newTask.setUndo();
        }
        TASKS.add(newTask);
    }

    private static void addEvent(String task, String startTime, String endTime) throws DateTimeException {
        LocalDateTime start = FairyDateTimeFormatter.parseDateTime(startTime);
        LocalDateTime end = FairyDateTimeFormatter.parseDateTime(endTime);
        // start should be no later than end
        if (end.isBefore(start)) {
            throw new DateTimeException("Start time is after end time");
        }
        Event newTask = new Event(task, start, end);
        TASKS.add(newTask);
        Ui.printStandardFormat("Yes, Master. I've added this task to your list:\n" + newTask.toString().indent(2) +
                "\nThere are " + TASKS.size() + " tasks in your list now.");
    }

    private static void addEventFromRecord(String task, String startTime, String endTime, String done)
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
        TASKS.add(newTask);
    }

    private static void deleteTask(int index) throws IndexOutOfBoundsException {
        if (index > TASKS.size()) {
            throw new IndexOutOfBoundsException("input " + index + " exceeds the size of list: " + TASKS.size());
        }
        Task removedTask = TASKS.remove(index - 1);
        Ui.printStandardFormat("Yes, Master. I've removed this task from your list:\n" +
                removedTask.toString().indent(2) + "\nThere are " + TASKS.size() + " tasks in your list now.");
    }

    private static void searchTaskByDate(String date) {
        LocalDate d = FairyDateTimeFormatter.parseDate(date);
        ArrayList<Task> tasks = new ArrayList<>();

        for (Task task : TASKS) {
            if (task instanceof Deadline && ((Deadline) task).getEndTime().toLocalDate().equals(d)) {
                tasks.add(task);
            } else if (task instanceof Event && !((d.isBefore(((Event) task).getStartTime().toLocalDate())) ||
                    d.isAfter(((Event) task).getEndTime().toLocalDate()))) {
                tasks.add(task);
            }
        }

        printTaskList(tasks);
    }

    private static int session() {
        while (true) {
            List<String> command = parseCommand(Ui.prompt());
            switch (command.get(0)) {
                case "bye":
                    return 0;
                case "mark":
                    try {
                        markTask(Integer.parseInt(command.get(1)));
                    } catch (IndexOutOfBoundsException e) {
                        if (command.size() < 2) {
                            Ui.argumentExceptionMessage();
                        } else {
                            Ui.indexOutOfBoundsMessage(e);
                        }
                    }
                    break;
                case "unmark":
                    try {
                        unmarkTask(Integer.parseInt(command.get(1)));
                    } catch (IndexOutOfBoundsException e) {
                        if (command.size() < 2) {
                            Ui.argumentExceptionMessage();
                        } else {
                            Ui.indexOutOfBoundsMessage(e);
                        }
                    }
                    break;
                case "list":
                    printTaskList(TASKS);
                    break;
                case "todo":
                    try {
                        addToDo(command.get(1));
                    } catch (IndexOutOfBoundsException e) {
                        Ui.argumentExceptionMessage();
                    }
                    break;
                case "deadline":
                    try {
                        addDeadline(command.get(1), command.get(2));
                    } catch (IndexOutOfBoundsException e) {
                        Ui.argumentExceptionMessage();
                    } catch (DateTimeException e) {
                        Ui.dateTimeExceptionMessage(e);
                    }
                    break;
                case "event":
                    try {
                        addEvent(command.get(1), command.get(2), command.get(3));
                    } catch (IndexOutOfBoundsException e) {
                        Ui.argumentExceptionMessage();
                    } catch (DateTimeException e) {
                        Ui.dateTimeExceptionMessage(e);
                    }
                    break;
                case "delete":
                    try {
                        deleteTask(Integer.parseInt(command.get(1)));
                    } catch (IndexOutOfBoundsException e) {
                        if (command.size() < 2) {
                            Ui.argumentExceptionMessage();
                        } else {
                            Ui.indexOutOfBoundsMessage(e);
                        }
                    }
                    break;
                case "searchByDate":
                    try {
                        searchTaskByDate(command.get(1));
                    } catch (IndexOutOfBoundsException e) {
                        Ui.argumentExceptionMessage();
                    } catch (DateTimeException e) {
                        Ui.dateTimeExceptionMessage(e);
                    }
                    break;
                default:
                    // Wrong Command
                    Ui.commandNotFoundMessage(command.get(0));
            }
        }
    }

    public static void main(String[] args) {
        Ui.greetMessage();
        Storage storage = new Storage(DIR, FILE);
        storage.readFile();
        session();
        storage.saveFile();
        Ui.exitMessage();
    }
}
