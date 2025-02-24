import java.time.DateTimeException;
import java.util.List;

public class Fairy {
    public static final String NAME = "Fairy";
    private static final String FILE = "./data/fairytasks.txt";
    private static final String DIR = "./data/";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Fairy(String name, String filePath, String fileDir) {
        ui = new Ui(NAME);
        storage = new Storage(fileDir, filePath);
        tasks = new TaskList();
        storage.readFile(tasks, ui);
    }

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
                throw new RuntimeException(e);
            }
        }
        storage.saveFile(tasks, ui);
        ui.exitMessage();
    }

    public static void main(String[] args) {
        new Fairy(NAME, FILE, DIR).run();
    }
}
