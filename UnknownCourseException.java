import java.io.IOException;

class UnknownCourseException extends RuntimeException {
    public UnknownCourseException() {
    }

    public UnknownCourseException(String message) {
        super(message);
    }
}