import java.io.IOException;

class LectureTimeCollisionException extends RuntimeException {
    public LectureTimeCollisionException() {
    }

    public LectureTimeCollisionException(String message) {
        super(message);
    }
}