package top.ptcc9.exceptions;


public class MulLoginException extends RuntimeException{
    public MulLoginException() {
    }

    public MulLoginException(String message) {
        super(message);
    }
}
