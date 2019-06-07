import com.google.common.collect.ImmutableMap;
import lombok.Getter;

import java.util.List;
import java.util.Map;

public class Location implements Comparable<Location> {
    private static final Map<String, int[]> MOVE_MAP =
        new ImmutableMap.Builder<String, int[]>()
            .put("E", new int[]{1, 0})
            .put("S", new int[]{0, -1})
            .put("W", new int[]{-1, 0})
            .put("N", new int[]{0, 1})
            .build();

    private static final Map<String, String[]> TURN_MAP =
        new ImmutableMap.Builder<String, String[]>()
            .put("E", new String[]{"N", "S"})
            .put("S", new String[]{"E", "W"})
            .put("W", new String[]{"S", "N"})
            .put("N", new String[]{"W", "E"})
            .build();

    @Getter
    private int x;
    @Getter
    private int y;
    @Getter
    private String orient;

    public Location(int x, int y, String orient) {
        this.x = x;
        this.y = y;
        this.orient = orient;
    }

    @Override
    public int compareTo(Location o) {
        if (x == o.x && y == o.y && orient.equals(o.orient)) {
            return 0;
        }
        return 1;
    }

    protected void update(InfoAndOrder infoAndOrder) throws MarsRoverException{
        infoAndOrder.getOrderList().stream()
            .forEach(order -> {
                if (order instanceof TurnOrder) {
                    updateOrient((TurnOrder) order);
                }
                if (order instanceof MoveOrder) {
                    updateCoordinate((MoveOrder) order);
                    checkBoundaryAndUpdateCoordinateIfNeed(infoAndOrder.getRegion());
                }
            });
    }

    private void checkBoundaryAndUpdateCoordinateIfNeed(Region region) {
        if (x > region.getWidth()) {
            x = region.getWidth();
            throw new MarsRoverException(String.format("Meet boundary, current coordinate is (%s, %s), and orient is %s", x, y, orient));
        }
        if (y > region.getHeight()) {
            y = region.getWidth();
            throw new MarsRoverException(String.format("Meet boundary, current coordinate is (%s, %s), and orient is %s", x, y, orient));
        }
    }

    private void updateOrient(TurnOrder order) {
        orient = order.left() ? getOrient(0) : getOrient(1);
    }

    private String getOrient(int index) {
        return TURN_MAP.get(orient)[index];
    }

    private void updateCoordinate(MoveOrder order) {
        updateX(order.forward(), order.getStep());
        updateY(order.forward(), order.getStep());
    }

    private void updateX(boolean forward, int step) {
        x = forward ? x + getStep(step, 0) : x - getStep(step, 0);
    }

    private void updateY(boolean forward, int step) {
        y = forward ? y + getStep(step, 1) : y - getStep(step, 1);
    }

    private int getStep(int step, int index) {
        return MOVE_MAP.get(orient)[index] * step;
    }
}
