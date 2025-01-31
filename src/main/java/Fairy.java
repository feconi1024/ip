import java.util.ArrayList;
import java.util.Scanner;

public class Fairy {
    private static final String NAME = "Fairy";
    private static final Scanner SC = new Scanner(System.in);
    private static final ArrayList<String> TASKS = new ArrayList<>();

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
        printStandardFormat("Hello, Master. This is " + NAME + ", your personal assistant.\nHow can I help you?");
    }

    private static void exit() {
        printStandardFormat("Goodbye, Master. Hope to see you again soon!");
    }

    private static String prompt() {
        System.out.print("> ");
        return SC.nextLine().trim();
    }

    private static void addTask(String task) {
        TASKS.add(task);
        printStandardFormat("Added: " + task);
    }

    private static void printTaskList() {
        String output = "";
        for (int i = 0; i < TASKS.size(); i++) {
            output += (i + 1) + ". " + TASKS.get(i) + "\n";
        }
        printStandardFormat(output);
    }

    private static int session() {
        while (true) {
            String command = prompt();
            switch (command) {
                case "bye":
                    return 0;
                case "list":
                    printTaskList();
                    break;
                default:
                    addTask(command);
            }
        }
    }

    public static void main(String[] args) {
        greet();
        session();
        exit();
    }
}
