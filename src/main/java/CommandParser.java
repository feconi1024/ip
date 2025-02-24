import java.util.ArrayList;
import java.util.List;

public class CommandParser {

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

            default:
                throw new InvalidCommandException();
        }
    }
}
