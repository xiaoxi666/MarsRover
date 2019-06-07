public class Rover {
    private InfoAndOrder infoAndOrder;

    public void receiveInfoAndOrder(InfoAndOrder infoAndOrder) {
        this.infoAndOrder = infoAndOrder;
    }

    public void executeOrder() {
        Region region = infoAndOrder.getRegion();
        Location location = infoAndOrder.getLocation();
        location.update(infoAndOrder.getOrderList());
    }

    public Location report() {
        return infoAndOrder.getLocation();
    }
}
