package top.ptcc9.exceptions;


public class LockMaxWaitingException extends RuntimeException {
    public LockMaxWaitingException() {
    }

    public LockMaxWaitingException(String message) {
        super(message);
    }
}
