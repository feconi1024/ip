public abstract class Command {

    public Command() {
        
    }

    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}
