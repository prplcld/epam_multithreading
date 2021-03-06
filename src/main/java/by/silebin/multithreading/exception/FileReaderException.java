package by.silebin.multithreading.exception;

public class FileReaderException extends Exception {
    public FileReaderException() {
    }

    public FileReaderException(String message) {
        super(message);
    }

    public FileReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileReaderException(Throwable cause) {
        super(cause);
    }
}
