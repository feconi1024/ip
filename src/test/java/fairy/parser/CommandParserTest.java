package fairy.parser;

import fairy.command.Command;

import fairy.exception.InvalidCommandException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CommandParserTest {

    @Test
    public void parseCommand_exceptionThrown1() {
        try {
            Command command = CommandParser.parseCommand("del 1");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("del", e.getMessage());
        }
    }
}
