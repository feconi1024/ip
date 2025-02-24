public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    public ExitCommand() {
        super();
    };

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {};

    @Override
    public boolean isExit() {
        return true;
    }
}
