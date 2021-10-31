package exception;

public class UniqueFieldDuplicationException extends RuntimeException {
    public UniqueFieldDuplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
