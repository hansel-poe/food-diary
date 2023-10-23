package model.exceptions;

public class InvalidMealTypeException extends Throwable {

    public InvalidMealTypeException() {

    }

    public InvalidMealTypeException(String msg) {
        super(msg);
    }
}
