import java.io.InputStream;
import java.io.PrintStream;
import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.Scanner;

public class Ui {
    private static final String NAME = "Fairy";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void printEmptyLine() {
        out.println();
    }

    public void printIndent(String content) {
        out.print(content.indent(4));
    }

    public void printStandardFormat(String content) {
        printEmptyLine();
        printIndent(content);
        printEmptyLine();
    }

    public void printTaskList(Iterator<Task> iterator) {
        if (!iterator.hasNext()) {
            printStandardFormat("No tasks found.");
            return;
        }
        StringBuilder output = new StringBuilder("Tasks found are listed as follows:\n");
        for (int i = 0; iterator.hasNext(); i++) {
            output.append((i + 1)).append(". ").append(iterator.next()).append("\n");
        }
        printStandardFormat(output.toString());
    }

    public void greetMessage() {
        printStandardFormat("Hello, Master. This is " + NAME + ", your personal assistant.\n" +
                "How can I help you?");
    }

    public void exitMessage() {
        printStandardFormat("Goodbye, Master. Hope to see you again soon!");
    }

    public void indexOutOfBoundsMessage(Exception e) {
        printStandardFormat("Index out of bounds exception: " + e.getMessage());
    }

    public void commandNotFoundMessage(String command) {
        printStandardFormat("Command not found: " + command);
    }

    public void argumentExceptionMessage() {
        printStandardFormat("Argument exception: No enough arguments.");
    }

    public void dateTimeExceptionMessage(DateTimeException e) {
        if (e instanceof DateTimeParseException) {
            printStandardFormat("Date time exception: Wrong format or illegal time. Correct format: YYYYMMDD hhmm");
        } else {
            printStandardFormat("Date time exception: " + e.getMessage());
        }
    }

    public String prompt() {
        out.print("> ");
        return in.nextLine().trim();
    }
}
