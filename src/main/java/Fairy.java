import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fairy {
    private static final String NAME = "Fairy";
    private static final Scanner SC = new Scanner(System.in);
    private static final ArrayList<Task> TASKS = new ArrayList<>();

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

    private static String prompt() {
        System.out.print("> ");
        return SC.nextLine().trim();
    }

    private static void addTask(String task) {
        TASKS.add(new Task(task));
        printStandardFormat("Added: " + task);
    }

    private static void markTask(int index) {
        TASKS.get(index - 1).setDo();
        printStandardFormat("Nice job, Master. I've marked this task as done: \n" +
                TASKS.get(index - 1).toString().indent(2));
    }

    private static void unmarkTask(int index) {
        TASKS.get(index - 1).setUndo();
        printStandardFormat("OK, Master. I've marked this task as not done yet: \n" +
                TASKS.get(index - 1).toString().indent(2));
    }

    private static void printTaskList() {
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

    private static void addDeadline(String task, String endTime) {
        Deadline newTask = new Deadline(task, endTime);
        TASKS.add(newTask);
        printStandardFormat("Yes, Master. I've added this task to your list:\n" + newTask.toString().indent(2) +
                "\nThere are " + TASKS.size() + " tasks in your list now.");
    }

    private static void addEvent(String task, String startTime, String endTime) {
        Event newTask = new Event(task, startTime, endTime);
        TASKS.add(newTask);
        printStandardFormat("Yes, Master. I've added this task to your list:\n" + newTask.toString().indent(2) +
                "\nThere are " + TASKS.size() + " tasks in your list now.");
    }

    private static int session() {
        while (true) {
            List<String> command = parseCommand(prompt());
            switch (command.get(0)) {
                case "bye":
                    return 0;
                case "mark":
                    markTask(Integer.parseInt(command.get(1)));
                    break;
                case "unmark":
                    unmarkTask(Integer.parseInt(command.get(1)));
                    break;
                case "list":
                    printTaskList();
                    break;
                case "todo":
                    addToDo(command.get(1));
                    break;
                case "deadline":
                    addDeadline(command.get(1), command.get(2));
                    break;
                case "event":
                    addEvent(command.get(1), command.get(2), command.get(3));
                    break;
                default:
                    addTask(String.join(" ", command));
            }
        }
    }

    public static void main(String[] args) {
        greet();
        session();
        exit();
    }
}
