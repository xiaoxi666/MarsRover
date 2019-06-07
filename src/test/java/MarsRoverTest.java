import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MarsRoverTest {

    @Test
    public void should_could_action_and_report_itself_0() {
        Region region = new Region(10, 10);
        Location initLocation = new Location(0, 0, "E");
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
        Region region = new Region(10, 10);
        Location initLocation = new Location(0, 0, "E");
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
        Region region = new Region(10, 10);
        Location initLocation = new Location(0, 0, "E");
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

        Region region = new Region(10, 10);
        Location initLocation = new Location(0, 0, "E");
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
}
