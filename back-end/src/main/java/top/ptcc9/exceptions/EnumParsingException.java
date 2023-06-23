package top.ptcc9.exceptions;


public class EnumParsingException extends RuntimeException{
    public EnumParsingException() {
    }

    public EnumParsingException(String message) {
        super(message);
    }
}
