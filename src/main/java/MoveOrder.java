import lombok.Getter;

public class MoveOrder implements Order{
    public static final String FORWARD = "f";
    public static final String BACKWARD = "b";
    private String move;
    @Getter
    private int step;

    public MoveOrder(String move, int step) {
        this.move = move;
        this.step = step;
    }

    public boolean forward() {
        checkParams();
        return "f".equals(move);
    }

    private void checkParams() {
        if (!FORWARD.equals(move) && !BACKWARD.equals(move)) {
            throw new MarsRoverException(MarsRoverException.ErrorCode.INVALID_ORDER);
        }
    }
}
