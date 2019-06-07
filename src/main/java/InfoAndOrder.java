import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.List;

@Getter
public class InfoAndOrder {
    private final Region region;
    private final Location location;
    private List<Order> orderList = Lists.newLinkedList();

    public InfoAndOrder(Region region, Location location) {
        this.region = region;
        this.location = location;
    }

    public InfoAndOrder addOrder(Order order) {
        orderList.add(order);
        return this;
    }

}
