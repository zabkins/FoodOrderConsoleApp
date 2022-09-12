package pl.zarczynski.foodorder.service;

import org.springframework.stereotype.Service;
import pl.zarczynski.foodorder.domain.Order;
import pl.zarczynski.foodorder.repository.OrderRepository;
import java.util.List;

@Service
public class DefaultOrderService implements OrderService{

    private final OrderRepository orderRepository;

    public DefaultOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
