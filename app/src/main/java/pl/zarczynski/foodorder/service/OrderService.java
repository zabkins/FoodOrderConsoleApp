package pl.zarczynski.foodorder.service;

import pl.zarczynski.foodorder.domain.Order;
import pl.zarczynski.foodorder.domain.OrderPosition;

import java.util.List;

public interface OrderService {
    void updateOrdersPositions(Order order, OrderPosition orderPosition);

    Order saveOrder(Order currentOrder);
}
