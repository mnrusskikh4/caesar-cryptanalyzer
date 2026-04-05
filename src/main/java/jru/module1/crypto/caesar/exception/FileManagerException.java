package jru.module1.crypto.caesar.exception;

public class FileManagerException extends RuntimeException {
    public FileManagerException(String message) {

        super(message);
    }

    public FileManagerException(String message, Throwable throwable) {

        super(message, throwable);
    }
}
