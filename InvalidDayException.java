import java.io.IOException;

class InvalidDayException extends RuntimeException {
    public InvalidDayException() {
    }

    public InvalidDayException(String message) {
        super(message);
    }
}