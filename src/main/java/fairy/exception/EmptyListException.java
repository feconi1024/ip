package fairy.exception;

/**
 * Signals that the list is empty such that operation cannot be executed on that.
 */
public class EmptyListException extends Exception {

    public EmptyListException() {
        super();
    }

    public EmptyListException(String message) {
        super(message);
    }
}
