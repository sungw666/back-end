package top.ptcc9.exceptions;


public class UserBannedException extends RuntimeException {
    public UserBannedException() {
    }

    public UserBannedException(String message) {
        super(message);
    }
}
