package fairy.ui;

import fairy.common.Messages;

/**
 * Formatter for graphic UI (GUI)
 */
public class Gui {

    /* Default name of the chatbot. */
    private static final String DEFAULT_NAME = "Fairy";

    public static String getGreetMessage() {
        return String.format(Messages.MESSAGE_GREETING, DEFAULT_NAME);
    }

    public static String getCommandNotFoundMessage(String command) {
        return String.format(Messages.MESSAGE_COMMAND_NOT_FOUND, command);
    }

    public static String getArgumentExceptionMessage() {
        return Messages.MESSAGE_ARGUMENT_EXCEPTION;
    }

    public static String getNumberParseExceptionMessage() {
        return Messages.MESSAGE_NUMBER_PARSE_EXCEPTION;
    }

    public static String getGeneralExceptionMessage(String message) {
        return String.format(Messages.MESSAGE_GENERAL_EXCEPTION, message);
    }
}
