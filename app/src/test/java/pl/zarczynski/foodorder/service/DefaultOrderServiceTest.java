package pl.zarczynski.foodorder.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.zarczynski.foodorder.domain.Dish;
import pl.zarczynski.foodorder.domain.Order;
import pl.zarczynski.foodorder.domain.OrderPosition;
import pl.zarczynski.foodorder.repository.DishRepository;
import pl.zarczynski.foodorder.repository.OrderPositionRepository;
import pl.zarczynski.foodorder.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DefaultOrderServiceTest {
    private OrderRepository orderRepository;
    private OrderPositionRepository orderPositionRepository;
    private OrderService orderService;

    @BeforeEach
    public void setUp(){
        orderRepository = mock(OrderRepository.class);
        orderPositionRepository = mock(OrderPositionRepository.class);
        orderService = new DefaultOrderService(orderRepository, orderPositionRepository);
    }
    @Test
    public void testIfDefaultOrderServiceCallsRepositoriesWhenSavingOrder(){
        Order order = new Order();
        order.setOrderPositions(new ArrayList<>());
        orderService.saveOrder(order);

        verify(orderPositionRepository,times(1)).saveAll(any());
        verify(orderRepository,times(1)).save(any(Order.class));
    }

    @Test
    public void testIfOrderServiceAssignsOrderAndOrderPositionCorrectly(){
        Order order = new Order();
        OrderPosition orderPosition = new OrderPosition();
        orderPosition.setDish(new Dish());
        orderPosition.setAmount(2);
        List<OrderPosition> orderPositions = List.of(orderPosition);

        orderService.updateOrdersPositions(order,orderPosition);

        assertEquals(orderPositions,order.getOrderPositions());
    }
}