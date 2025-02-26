package fairy.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import fairy.common.Messages;

/**
 * Text UI of the application.
 */
public class Ui {

    /* Default name of the chatbot. */
    private static final String DEFAULT_NAME = "Fairy";

    private final String name;
    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructor using default chatbot name, input and output interfaces.
     */
    public Ui() {
        this(DEFAULT_NAME, System.in, System.out);
    }

    /**
     * Constructor using default input and output interfaces.
     *
     * @param name Name of the chatbot.
     */
    public Ui(String name) {
        this(name, System.in, System.out);
    }

    /**
     * Constructor using default chatbot name.
     *
     * @param in Input interface.
     * @param out Output interface.
     */
    public Ui(InputStream in, PrintStream out) {
        this (DEFAULT_NAME, in, out);
    }

    /**
     * @param name Name of the chatbot.
     * @param in Input interface.
     * @param out Output interface.
     */
    public Ui(String name, InputStream in, PrintStream out) {
        this.name = name;
        this.in = new Scanner(in);
        this.out = out;
    }

    public void printEmptyLine() {
        out.println();
    }

    /**
     * Show the content with an indentation.
     *
     * @param content Content to be shown.
     */
    public void printIndent(String content) {
        out.print(content.indent(4));
    }

    /**
     * Show the content with blank lines above and below and default indentation.
     *
     * @param content Content to be shown.
     */
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

    /**
     * Prompts for the command and reads the text entered by the user.
     *
     * @return Command entered by the user.
     */
    public String prompt() {
        out.print("> ");
        return in.nextLine().trim();
    }
}
