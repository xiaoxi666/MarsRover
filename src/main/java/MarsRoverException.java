public class MarsRoverException extends RuntimeException {
    public MarsRoverException(ErrorCode errorCode) {
    }

    protected enum ErrorCode {
        INVALID_ORDER, MEET_BOUNDARY
    }
}
