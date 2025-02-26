package fairy.exception;

/**
 * Signals that the command word (or opcode) input is not supported.
 */
public class InvalidCommandException extends Exception{

    public InvalidCommandException(){
        super();
    }

    public InvalidCommandException(String message){
        super(message);
    }
}
