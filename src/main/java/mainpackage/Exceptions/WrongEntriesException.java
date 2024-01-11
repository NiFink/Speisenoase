package mainpackage.Exceptions;

public class WrongEntriesException extends RuntimeException{
    public WrongEntriesException(){
        super("User entered wrong entries on login!");
    }
}
