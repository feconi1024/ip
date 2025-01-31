import java.util.ArrayList;
import java.util.Scanner;

public class Fairy {
    private static final String NAME = "Fairy";
    private static final Scanner SC = new Scanner(System.in);
    private static final ArrayList<Task> TASKS = new ArrayList<>();

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

    private static int session() {
        while (true) {
            String commandOriginal = prompt();
            String[] command = commandOriginal.split(" ");
            switch (command[0]) {
                case "bye":
                    return 0;
                case "mark":
                    markTask(Integer.parseInt(command[1]));
                    break;
                case "unmark":
                    unmarkTask(Integer.parseInt(command[1]));
                    break;
                case "list":
                    printTaskList();
                    break;
                default:
                    addTask(commandOriginal);
            }
        }
    }

    public static void main(String[] args) {
        greet();
        session();
        exit();
    }
}
