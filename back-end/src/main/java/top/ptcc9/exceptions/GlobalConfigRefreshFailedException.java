package top.ptcc9.exceptions;


public class GlobalConfigRefreshFailedException extends RuntimeException{
    public GlobalConfigRefreshFailedException() {
    }

    public GlobalConfigRefreshFailedException(String message) {
        super(message);
    }
}
