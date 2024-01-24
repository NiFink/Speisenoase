package mainpackage.Exceptions;

import mainpackage.itempackage.ItemType;

public class InvalidTypeException extends RuntimeException{
    public InvalidTypeException(ItemType type){
        super("Items with the type of " + type +  " can not be created!");
    }
}
