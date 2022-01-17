import java.io.IOException;

class InvalidDurationException extends RuntimeException {
    public InvalidDurationException() {
    }

    public InvalidDurationException(String message) {
        super(message);
    }
}