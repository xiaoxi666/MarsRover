import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MarsRoverTest {

    @Test
    public void should_could_create_region() {
        Region region = getRegion();
        Assert.assertEquals(10, region.getHeight());
        Assert.assertEquals(10, region.getWidth());
    }

    @Test
    public void should_could_create_initLocation() {
        Location initLocation = getLocation();
        Assert.assertEquals(0, initLocation.getX());
        Assert.assertEquals(0, initLocation.getY());
    }

    private Location getLocation() {
        return new Location(0, 0, "E");
    }

    private Region getRegion() {
        return new Region(10, 10);
    }

    @Test
    public void should_could_action_and_report_itself_0() {
        Region region = getRegion();
        Location initLocation = getLocation();
        InfoAndOrder infoAndOrder =
            new InfoAndOrder(region, initLocation)
            .addOrder(new MoveOrder("f", 1))
            .addOrder(new TurnOrder("l"));

        Rover rover = new Rover();
        rover.receiveInfoAndOrder(infoAndOrder);
        rover.executeOrder();

        Location curLocation = rover.report();
        Assert.assertTrue(curLocation.compareTo(new Location(1, 0, "N")) == 0);
    }

    @Test
    public void should_could_action_and_report_itself_1() {
        Region region = getRegion();
        Location initLocation = getLocation();
        InfoAndOrder infoAndOrder =
            new InfoAndOrder(region, initLocation)
                .addOrder(new TurnOrder("l"))
                .addOrder(new MoveOrder("f", 1));

        Rover rover = new Rover();
        rover.receiveInfoAndOrder(infoAndOrder);
        rover.executeOrder();

        Location curLocation = rover.report();
        Assert.assertTrue(curLocation.compareTo(new Location(0, 1, "N")) == 0);
    }

    @Test
    public void should_could_action_and_report_itself_2() {
        Region region = getRegion();
        Location initLocation = getLocation();
        InfoAndOrder infoAndOrder =
            new InfoAndOrder(region, initLocation)
                .addOrder(new MoveOrder("f", 3))
                .addOrder(new TurnOrder("l"))
                .addOrder(new MoveOrder("f", 3))
                .addOrder(new TurnOrder("l"))
                .addOrder(new MoveOrder("b", 1));

        Rover rover = new Rover();
        rover.receiveInfoAndOrder(infoAndOrder);
        rover.executeOrder();

        Location curLocation = rover.report();
        Assert.assertTrue(curLocation.compareTo(new Location(4, 3, "W")) == 0);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void should_could_action_and_report_itself_meet_boundary() {
        thrown.expect(MarsRoverException.class);
        thrown.expectMessage("Meet boundary, current coordinate is (10, 3), and orient is W");

        Region region = getRegion();
        Location initLocation = getLocation();
        InfoAndOrder infoAndOrder =
            new InfoAndOrder(region, initLocation)
                .addOrder(new MoveOrder("f", 3))
                .addOrder(new TurnOrder("l"))
                .addOrder(new MoveOrder("f", 3))
                .addOrder(new TurnOrder("l"))
                .addOrder(new MoveOrder("b", 1))
                .addOrder(new MoveOrder("b", 7));

        Rover rover = new Rover();
        rover.receiveInfoAndOrder(infoAndOrder);
        rover.executeOrder();

//        Location curLocation = rover.report();
//        Assert.assertTrue(curLocation.compareTo(new Location(10, 3, "W")) == 0);
    }

    @Test
    public void should_could_action_and_report_itself_invalid_move_order() {
        thrown.expect(MarsRoverException.class);
        thrown.expectMessage("Invalid move order: k");

        Region region = getRegion();
        Location initLocation = getLocation();
        InfoAndOrder infoAndOrder =
            new InfoAndOrder(region, initLocation)
                .addOrder(new MoveOrder("f", 3))
                .addOrder(new TurnOrder("l"))
                .addOrder(new MoveOrder("k", 3))
                .addOrder(new TurnOrder("l"))
                .addOrder(new MoveOrder("b", 1))
                .addOrder(new MoveOrder("b", 7));

        Rover rover = new Rover();
        rover.receiveInfoAndOrder(infoAndOrder);
        rover.executeOrder();
    }

    @Test
    public void should_could_action_and_report_itself_invalid_turn_order() {
        thrown.expect(MarsRoverException.class);
        thrown.expectMessage("Invalid turn order: p");

        Region region = getRegion();
        Location initLocation = getLocation();
        InfoAndOrder infoAndOrder =
            new InfoAndOrder(region, initLocation)
                .addOrder(new MoveOrder("f", 3))
                .addOrder(new TurnOrder("l"))
                .addOrder(new MoveOrder("b", 3))
                .addOrder(new TurnOrder("p"))
                .addOrder(new MoveOrder("f", 1))
                .addOrder(new MoveOrder("b", 7));

        Rover rover = new Rover();
        rover.receiveInfoAndOrder(infoAndOrder);
        rover.executeOrder();
    }
}
