package mainpackage.Exceptions;

public class InvalidTypeException extends RuntimeException{
    public InvalidTypeException(){
        super("Items with this type can not be created!");
    }
}
