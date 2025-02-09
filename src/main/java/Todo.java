public class Todo extends Task {
    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toFileString() {
        return "TODO | " + super.toFileString();
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
