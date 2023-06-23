package top.ptcc9.exceptions;


public class IllegalParameterException extends RuntimeException {
    public IllegalParameterException() {
    }

    public IllegalParameterException(String message) {
        super(message);
    }
}
