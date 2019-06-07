import lombok.Getter;

public class MoveOrder implements Order{
    private String move;
    @Getter
    private int step;

    public MoveOrder(String move, int step) {
        this.move = move;
        this.step = step;
    }

    public boolean forward() {
        return "f".equals(move);
    }
}
