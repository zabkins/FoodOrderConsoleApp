package pl.zarczynski.foodorder.service;

import pl.zarczynski.foodorder.domain.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
}
