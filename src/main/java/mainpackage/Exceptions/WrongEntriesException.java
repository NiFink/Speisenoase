package mainpackage.Exceptions;

public class WrongEntriesException extends Exception{
    public WrongEntriesException(){
        super("User entered wrong entries on login!");
    }
}
