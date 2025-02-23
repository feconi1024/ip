import java.util.ArrayList;
import java.util.List;

public class CommandParser {

    public static List<String> parseCommand(String input) {
        List<String> result = new ArrayList<>();
        if (input == null || input.isEmpty()) {
            return result;
        }

        // Split into command and the rest of the string
        String[] cmdSplit = input.split(" ", 2);
        String command = cmdSplit[0];
        result.add(command);

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

        return result;
    }
}
