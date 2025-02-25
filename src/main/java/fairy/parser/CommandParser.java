package fairy.parser;

import java.util.ArrayList;
import java.util.List;

import fairy.command.Command;
import fairy.command.DeadlineCommand;
import fairy.command.DeleteCommand;
import fairy.command.EventCommand;
import fairy.command.ExitCommand;
import fairy.command.ListCommand;
import fairy.command.MarkCommand;
import fairy.command.SearchByDateCommand;
import fairy.command.TodoCommand;
import fairy.command.UnmarkCommand;
import fairy.exception.InvalidCommandException;

/**
 * Parses user input.
 */
public class CommandParser {

    /**
     * Parses user input into command for execution.
     *
     * @param input User input command string.
     * @return The command based on user input.
     * @throws InvalidCommandException If user input is null or command word is not supported.
     */
    public static Command parseCommand(String input) throws InvalidCommandException {
        List<String> result = new ArrayList<>();
        if (input == null || input.isEmpty()) {
            throw new InvalidCommandException();
        }

        // Split into command and the rest of the string
        String[] cmdSplit = input.split(" ", 2);
        String command = cmdSplit[0];

        if (cmdSplit.length > 1) {
            String rest = cmdSplit[1];
            // Split the rest on any occurrence of whitespace followed by /word
            String[] parts = rest.split("\\s+/\\w+");
            for (String part : parts) {
                String trimmedPart = part.trim();
                if (!trimmedPart.isEmpty()) {
                    result.add(trimmedPart);
                }
            }
        }

        // Return respective commands
        switch (command) {

            case TodoCommand.COMMAND_WORD:
                return new TodoCommand(result.get(0));

            case DeadlineCommand.COMMAND_WORD:
                return new DeadlineCommand(result.get(0), result.get(1));

            case EventCommand.COMMAND_WORD:
                return new EventCommand(result.get(0), result.get(1), result.get(2));

            case DeleteCommand.COMMAND_WORD:
                return new DeleteCommand(Integer.parseInt(result.get(0)));

            case ListCommand.COMMAND_WORD:
                return new ListCommand();

            case MarkCommand.COMMAND_WORD:
                return new MarkCommand(Integer.parseInt(result.get(0)));

            case UnmarkCommand.COMMAND_WORD:
                return new UnmarkCommand(Integer.parseInt(result.get(0)));

            case SearchByDateCommand.COMMAND_WORD:
                return new SearchByDateCommand(result.get(0));

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();

            default:
                throw new InvalidCommandException(command);
        }
    }
}
