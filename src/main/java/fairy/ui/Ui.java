package fairy.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Ui {

    private static final String DEFAULT_NAME = "fairy.Fairy";

    private final String name;
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(DEFAULT_NAME, System.in, System.out);
    }

    public Ui(String name) {
        this(name, System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this (DEFAULT_NAME, in, out);
    }

    public Ui(String name, InputStream in, PrintStream out) {
        this.name = name;
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

    public void greetMessage() {
        printStandardFormat(String.format(Messages.MESSAGE_GREETING, name));
    }

    public void exitMessage() {
        printStandardFormat(Messages.MESSAGE_EXIT);
    }

    public void indexOutOfBoundsMessage(String message) {
        printStandardFormat(String.format(Messages.MESSAGE_INDEX_OUT_OF_BOUNDS, message));
    }

    public void commandNotFoundMessage(String command) {
        printStandardFormat(String.format(Messages.MESSAGE_COMMAND_NOT_FOUND, command));
    }

    public void argumentExceptionMessage() {
        printStandardFormat(Messages.MESSAGE_ARGUMENT_EXCEPTION);
    }

    public void numberParseExceptionMessage() {
        printStandardFormat(Messages.MESSAGE_NUMBER_PARSE_EXCEPTION);
    }

    public void generalExceptionMessage(String message) {
        printStandardFormat(String.format(Messages.MESSAGE_GENERAL_EXCEPTION, message));
    }

    public void dateTimeExceptionMessage(DateTimeException e) {
        if (e instanceof DateTimeParseException) {
            printStandardFormat(Messages.MESSAGE_DATETIME_PARSE_EXCEPTION);
        } else {
            printStandardFormat(String.format(Messages.MESSAGE_DATETIME_EXCEPTION, e.getMessage()));
        }
    }

    public String prompt() {
        out.print("> ");
        return in.nextLine().trim();
    }
}
