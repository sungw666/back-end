package top.ptcc9.exceptions;


public class TokenExpireException extends RuntimeException {
    public TokenExpireException() {
    }

    public TokenExpireException(String message) {
        super(message);
    }
}
