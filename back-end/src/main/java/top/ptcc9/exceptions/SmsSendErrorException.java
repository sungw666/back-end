package top.ptcc9.exceptions;


public class SmsSendErrorException extends RuntimeException{
    public SmsSendErrorException() {
    }

    public SmsSendErrorException(String message) {
        super(message);
    }
}
