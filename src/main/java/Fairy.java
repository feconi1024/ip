import java.time.DateTimeException;
import java.util.List;

public class Fairy {
    public static final TaskList TASKS = new TaskList();
    private static final String FILE = "./data/fairytasks.txt";
    private static final String DIR = "./data/";

    private static int session() {
        while (true) {
            List<String> command = CommandParser.parseCommand(Ui.prompt());
            switch (command.get(0)) {
                case "bye":
                    return 0;
                case "mark":
                    try {
                        TASKS.markTask(Integer.parseInt(command.get(1)));
                    } catch (IndexOutOfBoundsException e) {
                        if (command.size() < 2) {
                            Ui.argumentExceptionMessage();
                        } else {
                            Ui.indexOutOfBoundsMessage(e);
                        }
                    }
                    break;
                case "unmark":
                    try {
                        TASKS.unmarkTask(Integer.parseInt(command.get(1)));
                    } catch (IndexOutOfBoundsException e) {
                        if (command.size() < 2) {
                            Ui.argumentExceptionMessage();
                        } else {
                            Ui.indexOutOfBoundsMessage(e);
                        }
                    }
                    break;
                case "list":
                    Ui.printTaskList(TASKS.iterator());
                    break;
                case "todo":
                    try {
                        TASKS.addToDo(command.get(1));
                    } catch (IndexOutOfBoundsException e) {
                        Ui.argumentExceptionMessage();
                    }
                    break;
                case "deadline":
                    try {
                        TASKS.addDeadline(command.get(1), command.get(2));
                    } catch (IndexOutOfBoundsException e) {
                        Ui.argumentExceptionMessage();
                    } catch (DateTimeException e) {
                        Ui.dateTimeExceptionMessage(e);
                    }
                    break;
                case "event":
                    try {
                        TASKS.addEvent(command.get(1), command.get(2), command.get(3));
                    } catch (IndexOutOfBoundsException e) {
                        Ui.argumentExceptionMessage();
                    } catch (DateTimeException e) {
                        Ui.dateTimeExceptionMessage(e);
                    }
                    break;
                case "delete":
                    try {
                        TASKS.deleteTask(Integer.parseInt(command.get(1)));
                    } catch (IndexOutOfBoundsException e) {
                        if (command.size() < 2) {
                            Ui.argumentExceptionMessage();
                        } else {
                            Ui.indexOutOfBoundsMessage(e);
                        }
                    }
                    break;
                case "searchByDate":
                    try {
                        TASKS.searchTaskByDate(command.get(1));
                    } catch (IndexOutOfBoundsException e) {
                        Ui.argumentExceptionMessage();
                    } catch (DateTimeException e) {
                        Ui.dateTimeExceptionMessage(e);
                    }
                    break;
                default:
                    // Wrong Command
                    Ui.commandNotFoundMessage(command.get(0));
            }
        }
    }

    public static void main(String[] args) {
        Ui.greetMessage();
        Storage storage = new Storage(DIR, FILE);
        storage.readFile();
        session();
        storage.saveFile();
        Ui.exitMessage();
    }
}
