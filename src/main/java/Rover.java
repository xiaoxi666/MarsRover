public class Rover {
    private InfoAndOrder infoAndOrder;

    public void receiveInfoAndOrder(InfoAndOrder infoAndOrder) {
        this.infoAndOrder = infoAndOrder;
    }

    public void executeOrder() {
        Location location = infoAndOrder.getLocation();
        location.update(infoAndOrder);
    }

    public Location report() {
        return infoAndOrder.getLocation();
    }
}
