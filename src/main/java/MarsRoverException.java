public class MarsRoverException extends RuntimeException {
    private String message;

    public MarsRoverException(String message) {

        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
