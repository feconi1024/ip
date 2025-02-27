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
     * Constructor using default input and output interfaces.
     *
     * @param name Name of the chatbot.
     */
    public Ui(String name) {
        this(name, System.in, System.out);
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

    public void showEmptyLine() {
        out.println();
    }

    /**
     * Shows the content with an indentation.
     *
     * @param content Content to be shown.
     */
    public void showIndentation(String content) {
        out.print(content.indent(4));
    }

    /**
     * Shows the content with blank lines above and below and default indentation.
     *
     * @param content Content to be shown.
     */
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

    /**
     * Prompts for the command and reads the text entered by the user.
     *
     * @return Command entered by the user.
     */
    public String getUserCommand() {
        out.print("> ");
        return in.nextLine().trim();
    }
}
