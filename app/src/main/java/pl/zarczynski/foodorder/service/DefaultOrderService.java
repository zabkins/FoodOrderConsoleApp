package pl.zarczynski.foodorder.service;

import org.springframework.stereotype.Service;
import pl.zarczynski.foodorder.domain.Order;
import pl.zarczynski.foodorder.domain.OrderPosition;
import pl.zarczynski.foodorder.repository.OrderPositionRepository;
import pl.zarczynski.foodorder.repository.OrderRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DefaultOrderService implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderPositionRepository orderPositionRepository;

    public DefaultOrderService(OrderRepository orderRepository, OrderPositionRepository orderPositionRepository) {
        this.orderRepository = orderRepository;
        this.orderPositionRepository = orderPositionRepository;
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
            if(!isAlreadyAdded && orderPosition.getAmount() != 0){
                order.addPosition(orderPosition);
            }
        } else {
            orderPositions = new ArrayList<>();
            if(orderPosition.getAmount() != 0){
                orderPositions.add(orderPosition);
            }
            order.setOrderPositions(orderPositions);
        }
        order.updatePrice();
    }

    @Override
    public Order saveOrder(Order currentOrder) {
        List<OrderPosition> orderPositions = currentOrder.getOrderPositions();
        orderPositionRepository.saveAll(orderPositions);
        return orderRepository.save(currentOrder);
    }
}
