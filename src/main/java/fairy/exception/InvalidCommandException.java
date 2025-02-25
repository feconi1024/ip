package fairy.exception;

/**
 * Signals that the command word (or opcode) input does not exist.
 */
public class InvalidCommandException extends Exception{

    public InvalidCommandException(){
        super();
    }

    public InvalidCommandException(String message){
        super(message);
    }
}
