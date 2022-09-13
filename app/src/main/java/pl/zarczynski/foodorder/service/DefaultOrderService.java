package pl.zarczynski.foodorder.service;

import org.springframework.stereotype.Service;
import pl.zarczynski.foodorder.domain.Order;
import pl.zarczynski.foodorder.domain.OrderPosition;
import pl.zarczynski.foodorder.repository.OrderRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DefaultOrderService implements OrderService{

    private final OrderRepository orderRepository;

    public DefaultOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void updateOrdersPositions(Order order, OrderPosition orderPosition) {
        List<OrderPosition> orderPositions = order.getOrderPositions();
        if(orderPositions != null){
            boolean isAlreadyAdded = false;
            for (OrderPosition currentOrderPosition : orderPositions) {
                if(currentOrderPosition.getDish().equals(orderPosition.getDish())){
                    isAlreadyAdded = true;
                    if(orderPosition.getAmount() == 0){
                        order.removePosition(currentOrderPosition);
                    } else {
                        currentOrderPosition.setAmount(orderPosition.getAmount());
                    }
                }
            }
            if(!isAlreadyAdded){
                order.addPosition(orderPosition);
            }
        } else {
            orderPositions = new ArrayList<>();
            orderPositions.add(orderPosition);
            order.setOrderPositions(orderPositions);
        }
        order.updatePrice();
    }
}
