import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Ui {
    private static final Scanner SC = new Scanner(System.in);
    private static final String NAME = "Fairy";

    public static void printEmptyLine() {
        System.out.println();
    }

    public static void printIndent(String content) {
        System.out.print(content.indent(4));
    }

    public static void printStandardFormat(String content) {
        Ui.printEmptyLine();
        Ui.printIndent(content);
        Ui.printEmptyLine();
    }

    public static void greetMessage() {
        Ui.printStandardFormat("Hello, Master. This is " + NAME + ", your personal assistant.\n" +
                "How can I help you?");
    }

    public static void exitMessage() {
        Ui.printStandardFormat("Goodbye, Master. Hope to see you again soon!");
    }

    public static void indexOutOfBoundsMessage(Exception e) {
        Ui.printStandardFormat("Index out of bounds exception: " + e.getMessage());
    }

    public static void commandNotFoundMessage(String command) {
        Ui.printStandardFormat("Command not found: " + command);
    }

    public static void argumentExceptionMessage() {
        Ui.printStandardFormat("Argument exception: No enough arguments.");
    }

    public static void dateTimeExceptionMessage(DateTimeException e) {
        if (e instanceof DateTimeParseException) {
            Ui.printStandardFormat("Date time exception: Wrong format or illegal time. Correct format: YYYYMMDD hhmm");
        } else {
            Ui.printStandardFormat("Date time exception: " + e.getMessage());
        }
    }

    public static String prompt() {
        System.out.print("> ");
        return SC.nextLine().trim();
    }
}
