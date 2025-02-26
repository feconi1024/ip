package fairy;

import fairy.command.Command;
import fairy.exception.InvalidCommandException;
import fairy.parser.CommandParser;
import fairy.storage.Storage;
import fairy.task.TaskList;
import fairy.ui.Ui;

public class Fairy {
    public static final String NAME = "Fairy";
    private static final String FILE = "./data/fairytasks.txt";
    private static final String DIR = "./data/";

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Fairy(String name, String filePath, String fileDir) {
        ui = new Ui(name);
        storage = new Storage(fileDir, filePath);
        tasks = new TaskList();
        storage.readFile(tasks, ui);
    }

    public void run() {
        ui.showGreetMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getUserCommand();
                Command c = CommandParser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
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
        storage.saveFile(tasks, ui);
        ui.showExitMessage();
    }

    public static void main(String[] args) {
        new Fairy(NAME, FILE, DIR).run();
    }
}
