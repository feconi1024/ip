package fairy.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import fairy.common.Messages;

public class Ui {

    private static final String DEFAULT_NAME = "Fairy";

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

    public void showEmptyLine() {
        out.println();
    }

    public void showIndentation(String content) {
        out.print(content.indent(4));
    }

    public void showStandardFormat(String content) {
        showEmptyLine();
        showIndentation(content);
        showEmptyLine();
    }

    public void showGreetMessage() {
        showStandardFormat(String.format(Messages.MESSAGE_GREETING, name));
    }

    public void showExitMessage() {
        showStandardFormat(Messages.MESSAGE_EXIT);
    }

    public void showIndexOutOfBoundsMessage(String message) {
        showStandardFormat(String.format(Messages.MESSAGE_INDEX_OUT_OF_BOUNDS, message));
    }

    public void showCommandNotFoundMessage(String command) {
        showStandardFormat(String.format(Messages.MESSAGE_COMMAND_NOT_FOUND, command));
    }

    public void showArgumentExceptionMessage() {
        showStandardFormat(Messages.MESSAGE_ARGUMENT_EXCEPTION);
    }

    public void showNumberParseExceptionMessage() {
        showStandardFormat(Messages.MESSAGE_NUMBER_PARSE_EXCEPTION);
    }

    public void showGeneralExceptionMessage(String message) {
        showStandardFormat(String.format(Messages.MESSAGE_GENERAL_EXCEPTION, message));
    }

    public void showDateTimeExceptionMessage(DateTimeException e) {
        if (e instanceof DateTimeParseException) {
            showStandardFormat(Messages.MESSAGE_DATETIME_PARSE_EXCEPTION);
        } else {
            showStandardFormat(String.format(Messages.MESSAGE_DATETIME_EXCEPTION, e.getMessage()));
        }
    }

    public String getUserCommand() {
        out.print("> ");
        return in.nextLine().trim();
    }
}
