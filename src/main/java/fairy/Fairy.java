package fairy;

import fairy.command.Command;
import fairy.exception.InvalidCommandException;
import fairy.parser.CommandParser;
import fairy.storage.Storage;
import fairy.task.TaskList;
import fairy.ui.Gui;
import fairy.ui.Ui;


/**
 * Entry point of Fairy chatbot application.
 * Initializes the application and starts the interaction with the user.
 */
public class Fairy {
    /* Name of the chatbot appearing in messages. */
    public static final String NAME = "Fairy";

    /* Path of the task record file to be stored. */
    public static final String FILE = "./data/fairytasks.txt";

    /* Directory of the task record file. Used to create the directory if it does not exist. */
    public static final String DIR = "./data/";

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private boolean isExitRequested = false;

    /**
     * Simple constructor with no argument, using default values.
     */
    public Fairy() {
        this(NAME, FILE, DIR);
    }

    /**
     * Constructor of the application.
     * Initializes objects that are essential for the application.
     *
     * @param name Name of chatbot.
     * @param filePath Path to file storing records of tasks.
     * @param fileDir Directory holding file.
     */
    public Fairy(String name, String filePath, String fileDir) {
        ui = new Ui(name);
        storage = new Storage(fileDir, filePath);
        tasks = new TaskList();
        storage.readFile(tasks, ui);
    }

    public boolean shouldExit() {
        return isExitRequested;
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        // start application
        ui.showGreetMessage();
        boolean isExit = false;

        // repeatedly prompt for and process commands
        while (!isExit) {
            try {
                String fullCommand = ui.getUserCommand();
                Command c = CommandParser.parseCommand(fullCommand);
                c.executeTextUi(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IndexOutOfBoundsException e) {
                ui.showArgumentExceptionMessage();
            } catch (NumberFormatException e) {
                ui.showNumberParseExceptionMessage();
            } catch (InvalidCommandException e) {
                ui.showCommandNotFoundMessage(e.getMessage());
            } catch (Exception e) {
                ui.showGeneralExceptionMessage(e.getMessage());
            }
        }

        // save and exit
        storage.saveFile(tasks, ui);
        ui.showExitMessage();
    }

    public String getResponse(String fullCommand) {
        try {
            Command c = CommandParser.parseCommand(fullCommand);
            if (c.isExit()) {
                // save and exit
                isExitRequested = true;
                storage.saveFile(tasks, ui);
            }
            return c.execute(tasks, storage);
        } catch (IndexOutOfBoundsException e) {
            return Gui.getArgumentExceptionMessage();
        } catch (NumberFormatException e) {
            return Gui.getNumberParseExceptionMessage();
        } catch (InvalidCommandException e) {
            return Gui.getCommandNotFoundMessage(e.getMessage());
        } catch (Exception e) {
            return Gui.getGeneralExceptionMessage(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Fairy(NAME, FILE, DIR).run();
    }
}
