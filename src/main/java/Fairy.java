import java.util.Scanner;

public class Fairy {
    private static final String NAME = "Fairy";
    private static final Scanner SC = new Scanner(System.in);

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
        printStandardFormat("Goodbye, Master. Hope to see you again soon!\n");
    }

    private static String prompt() {
        System.out.print("> ");
        return SC.next().trim();
    }

    private static int session() {
        while (true) {
            String command = prompt();
            switch (command) {
                case "bye":
                    return 0;
                default:
                    printStandardFormat(command);
            }
        }
    }

    public static void main(String[] args) {
        greet();
        session();
        exit();
    }
}
