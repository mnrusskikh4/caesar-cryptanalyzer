package jru.module1.crypto.caesar.exception;

public class AlphabetException extends RuntimeException {
    public AlphabetException(String message) {

        super(message);
    }

    public AlphabetException(String message, Throwable throwable) {

        super(message, throwable);
    }
}
