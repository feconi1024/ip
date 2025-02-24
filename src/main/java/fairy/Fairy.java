package fairy;

import fairy.command.Command;
import fairy.exception.InvalidCommandException;
import fairy.parser.CommandParser;
import fairy.storage.Storage;
import fairy.task.TaskList;
import fairy.ui.Ui;


/**
 * Entry point of Fairy chatbot application.
 * Initializes the application and starts the interaction with the user.
 */
public class Fairy {
    /* Name of the chatbot appearing in messages. */
    public static final String NAME = "Fairy";

    /* Path of the task record file to be stored. */
    private static final String FILE = "./data/fairytasks.txt";

    /* Directory of the task record file. Used to create the directory if it does not exist. */
    private static final String DIR = "./data/";

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructor of the application.
     * Initializes objects that are essential for the application.
     *
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

    /**
     * Runs the chatbot.
     */
    public void run() {
        ui.greetMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.prompt();
                Command c = CommandParser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IndexOutOfBoundsException e) {
                ui.argumentExceptionMessage();
            } catch (NumberFormatException e) {
                ui.numberParseExceptionMessage();
            } catch (InvalidCommandException e) {
                ui.commandNotFoundMessage(e.getMessage());
            } catch (Exception e) {
                ui.generalExceptionMessage(e.getMessage());
            }
        }
        storage.saveFile(tasks, ui);
        ui.exitMessage();
    }

    public static void main(String[] args) {
        new Fairy(NAME, FILE, DIR).run();
    }
}
