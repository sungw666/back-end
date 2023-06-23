package top.ptcc9.exceptions;


public class NoAuthException extends RuntimeException{
    public NoAuthException() {
    }

    public NoAuthException(String message) {
        super(message);
    }
}