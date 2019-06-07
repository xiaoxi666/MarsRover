public class TurnOrder implements Order{
    public static final String LEFT = "l";
    public static final String RIGHT = "r";
    private String turn;

    public TurnOrder(String turn) {
        this.turn = turn;
    }

    public boolean left() {
        checkParams();
        return LEFT.equals(turn);
    }

    private void checkParams() {
        if (!LEFT.equals(turn) && !RIGHT.equals(turn)) {
            throw new MarsRoverException(String.format("Invalid turn order: %s", turn));
        }
    }

}
