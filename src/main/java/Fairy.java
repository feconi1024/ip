import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fairy {
    private static final String NAME = "Fairy";
    private static final Scanner SC = new Scanner(System.in);
    private static final ArrayList<Task> TASKS = new ArrayList<>();
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

    private static void printEmptyLine() {
        System.out.println();
    }

    private static void printIndent(String content) {
        System.out.print(content.indent(4));
    }

    private static void printStandardFormat(String content) {
        printEmptyLine();
        printIndent(content);
        printEmptyLine();
    }

    private static void greet() {
        printStandardFormat("Hello, Master. This is " + NAME + ", your personal assistant.\n" +
                "How can I help you?");
    }

    private static void exit() {
        printStandardFormat("Goodbye, Master. Hope to see you again soon!");
    }

    private static void indexOutOfBoundsMessage(Exception e) {
        printStandardFormat("Index out of bounds exception: " + e.getMessage());
    }

    private static void commandNotFoundMessage(String command) {
        printStandardFormat("Command not found: " + command);
    }

    private static void argumentExceptionMessage() {
        printStandardFormat("Argument exception: No enough arguments.");
    }

    private static void dateTimeParseExceptionMessage() {
        printStandardFormat("Date time exception: Wrong format. Correct format: YYYYMMDD hhmm");
    }

    private static String prompt() {
        System.out.print("> ");
        return SC.nextLine().trim();
    }

    private static void addTask(String task) {
        TASKS.add(new Task(task));
        printStandardFormat("Added: " + task);
    }

    private static int addTaskFromRecord(String record) {
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
        printStandardFormat("Nice job, Master. I've marked this task as done: \n" +
                TASKS.get(index - 1).toString().indent(2));
    }

    private static void unmarkTask(int index) throws IndexOutOfBoundsException {
        if (index > TASKS.size()) {
            throw new IndexOutOfBoundsException("input " + index + " exceeds the size of list: " + TASKS.size());
        }
        TASKS.get(index - 1).setUndo();
        printStandardFormat("OK, Master. I've marked this task as not done yet: \n" +
                TASKS.get(index - 1).toString().indent(2));
    }

    private static void printTaskList() {
        if (TASKS.isEmpty()) {
            printStandardFormat("No tasks found.");
            return;
        }
        String output = "Master, here are the tasks in your list:\n";
        for (int i = 0; i < TASKS.size(); i++) {
            output += (i + 1) + ". " + TASKS.get(i) + "\n";
        }
        printStandardFormat(output);
    }

    private static void addToDo(String task) {
        Todo newTask = new Todo(task);
        TASKS.add(newTask);
        printStandardFormat("Yes, Master. I've added this task to your list:\n" + newTask.toString().indent(2) +
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
        printStandardFormat("Yes, Master. I've added this task to your list:\n" + newTask.toString().indent(2) +
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

    private static void addEvent(String task, String startTime, String endTime) {
        Event newTask = new Event(task, FairyDateTimeFormatter.parseDateTime(startTime), FairyDateTimeFormatter.parseDateTime(endTime));
        TASKS.add(newTask);
        printStandardFormat("Yes, Master. I've added this task to your list:\n" + newTask.toString().indent(2) +
                "\nThere are " + TASKS.size() + " tasks in your list now.");
    }

    private static void addEventFromRecord(String task, String startTime, String endTime, String done) {
        Event newTask = new Event(task, FairyDateTimeFormatter.parseDateTime(startTime), FairyDateTimeFormatter.parseDateTime(endTime));
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
        printStandardFormat("Yes, Master. I've removed this task from your list:\n" +
                removedTask.toString().indent(2) + "\nThere are " + TASKS.size() + " tasks in your list now.");
    }

    private static void readFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE));
            String line;
            int effectiveLines = 0;
            int totalLines = 0;
            while ((line = reader.readLine()) != null) {
                effectiveLines += addTaskFromRecord(line);
                totalLines += 1;
            }
            reader.close();
            if (effectiveLines != totalLines) {
                printStandardFormat(String.format("%d of %d lines added to the list of tasks. \n" +
                        "Failures may because of incorrect format or corrupted file.", effectiveLines, totalLines));
            } else {
                printStandardFormat(String.format("%d of %d lines added to the list of tasks.",
                        totalLines, effectiveLines));
            }

        } catch (FileNotFoundException e) {
            printStandardFormat("No record found. List starts empty.");
        } catch (IOException e) {
            printStandardFormat("I/O exception: " + e.getMessage());
        }
    }

    private static void saveFile() {
        File dir = new File(DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE));
            for (Task task : TASKS) {
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
            printStandardFormat("Tasks saved.");
        } catch (IOException e) {
            printStandardFormat("Error saving file: " + e.getMessage());
        }
    }

    private static int session() {
        while (true) {
            List<String> command = parseCommand(prompt());
            switch (command.get(0)) {
                case "bye":
                    return 0;
                case "mark":
                    try {
                        markTask(Integer.parseInt(command.get(1)));
                    } catch (IndexOutOfBoundsException e) {
                        if (command.size() < 2) {
                            argumentExceptionMessage();
                        } else {
                            indexOutOfBoundsMessage(e);
                        }
                    }
                    break;
                case "unmark":
                    try {
                        unmarkTask(Integer.parseInt(command.get(1)));
                    } catch (IndexOutOfBoundsException e) {
                        if (command.size() < 2) {
                            argumentExceptionMessage();
                        } else {
                            indexOutOfBoundsMessage(e);
                        }
                    }
                    break;
                case "list":
                    printTaskList();
                    break;
                case "todo":
                    try {
                        addToDo(command.get(1));
                    } catch (IndexOutOfBoundsException e) {
                        argumentExceptionMessage();
                    }
                    break;
                case "deadline":
                    try {
                        addDeadline(command.get(1), command.get(2));
                    } catch (IndexOutOfBoundsException e) {
                        argumentExceptionMessage();
                    } catch (DateTimeParseException e) {
                        dateTimeParseExceptionMessage();
                    }
                    break;
                case "event":
                    try {
                        addEvent(command.get(1), command.get(2), command.get(3));
                    } catch (IndexOutOfBoundsException e) {
                        argumentExceptionMessage();
                    } catch (DateTimeParseException e) {
                        dateTimeParseExceptionMessage();
                    }
                    break;
                case "delete":
                    try {
                        deleteTask(Integer.parseInt(command.get(1)));
                    } catch (IndexOutOfBoundsException e) {
                        if (command.size() < 2) {
                            argumentExceptionMessage();
                        } else {
                            indexOutOfBoundsMessage(e);
                        }
                    }
                    break;
                default:
                    // Wrong Command
                    commandNotFoundMessage(command.get(0));
            }
        }
    }

    public static void main(String[] args) {
        greet();
        readFile();
        session();
        saveFile();
        exit();
    }
}
