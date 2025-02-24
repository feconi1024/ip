import java.time.DateTimeException;
import java.util.List;

public class Fairy {
    public static final String NAME = "Fairy";
    public static final TaskList TASKS = new TaskList();
    private static final String FILE = "./data/fairytasks.txt";
    private static final String DIR = "./data/";

    private static int session(Ui ui) {
        while (true) {
            List<String> command = CommandParser.parseCommand(ui.prompt());
            switch (command.get(0)) {
                case "bye":
                    return 0;
                case "mark":
                    try {
                        TASKS.markTask(Integer.parseInt(command.get(1)));
                    } catch (IndexOutOfBoundsException e) {
                        if (command.size() < 2) {
                            ui.argumentExceptionMessage();
                        } else {
                            ui.indexOutOfBoundsMessage(e);
                        }
                    }
                    break;
                case "unmark":
                    try {
                        TASKS.unmarkTask(Integer.parseInt(command.get(1)));
                    } catch (IndexOutOfBoundsException e) {
                        if (command.size() < 2) {
                            ui.argumentExceptionMessage();
                        } else {
                            ui.indexOutOfBoundsMessage(e);
                        }
                    }
                    break;
                case "list":
                    ui.printTaskList(TASKS.iterator());
                    break;
                case "todo":
                    try {
                        TASKS.addToDo(command.get(1));
                    } catch (IndexOutOfBoundsException e) {
                        ui.argumentExceptionMessage();
                    }
                    break;
                case "deadline":
                    try {
                        TASKS.addDeadline(command.get(1), command.get(2));
                    } catch (IndexOutOfBoundsException e) {
                        ui.argumentExceptionMessage();
                    } catch (DateTimeException e) {
                        ui.dateTimeExceptionMessage(e);
                    }
                    break;
                case "event":
                    try {
                        TASKS.addEvent(command.get(1), command.get(2), command.get(3));
                    } catch (IndexOutOfBoundsException e) {
                        ui.argumentExceptionMessage();
                    } catch (DateTimeException e) {
                        ui.dateTimeExceptionMessage(e);
                    }
                    break;
                case "delete":
                    try {
                        TASKS.deleteTask(Integer.parseInt(command.get(1)));
                    } catch (IndexOutOfBoundsException e) {
                        if (command.size() < 2) {
                            ui.argumentExceptionMessage();
                        } else {
                            ui.indexOutOfBoundsMessage(e);
                        }
                    }
                    break;
                case "searchByDate":
                    try {
                        TASKS.searchTaskByDate(command.get(1));
                    } catch (IndexOutOfBoundsException e) {
                        ui.argumentExceptionMessage();
                    } catch (DateTimeException e) {
                        ui.dateTimeExceptionMessage(e);
                    }
                    break;
                default:
                    // Wrong Command
                    ui.commandNotFoundMessage(command.get(0));
            }
        }
    }

    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.greetMessage();
        Storage storage = new Storage(DIR, FILE);
        storage.readFile(TASKS, ui);
        session(ui);
        storage.saveFile(TASKS, ui);
        ui.exitMessage();
    }
}
