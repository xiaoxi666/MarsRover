public class TurnOrder implements Order{
    private String turn;

    public TurnOrder(String turn) {
        this.turn = turn;
    }

    public boolean left() {
        return "l".equals(turn);
    }

}
