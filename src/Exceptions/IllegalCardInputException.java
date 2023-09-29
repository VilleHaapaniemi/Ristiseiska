package Exceptions;

public class IllegalCardInputException extends RuntimeException{
    public IllegalCardInputException(String message) {
        super(message);
    }
}
