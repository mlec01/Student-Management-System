import java.io.IOException;

class InvalidTimeException extends RuntimeException {
    public InvalidTimeException() {
    }

    public InvalidTimeException(String message) {
        super(message);
    }
}