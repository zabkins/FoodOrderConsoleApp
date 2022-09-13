package pl.zarczynski.foodorder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.zarczynski.foodorder.domain.Dish;
import pl.zarczynski.foodorder.domain.Order;
import pl.zarczynski.foodorder.domain.OrderPosition;
import pl.zarczynski.foodorder.service.DishService;
import pl.zarczynski.foodorder.service.OrderService;
import pl.zarczynski.foodorder.view.View;

import java.util.ArrayList;
import java.util.Set;

@Component
public class FoodOrder implements CommandLineRunner {
    private final DishService dishService;
    private final OrderService orderService;
    private final View view;

    public FoodOrder(DishService dishService, OrderService orderService, View view) {
        this.dishService = dishService;
        this.orderService = orderService;
        this.view = view;
    }

    @Override
    public void run(String... args) throws Exception {
        view.welcomeClient();
        askClientToOrder();
    }

    private void askClientToOrder() throws Exception {
        Order currentOrder = new Order();
        Set<Dish> allDishes = dishService.getAllDishesWithIngredients();
        view.printDishes(new ArrayList<>(allDishes));
        addChosenDishToOrder(currentOrder, allDishes);
        while (!view.promptForOrderChange()) {
            addChosenDishToOrder(currentOrder, allDishes);
        }
        saveOrderAndPrintConfirmation(currentOrder);
        if(view.askToMakeAnotherOrder()){
            askClientToOrder();
        } else {
            System.exit(0);
        }
    }

    private void saveOrderAndPrintConfirmation(Order currentOrder) {
        Order savedOrder = orderService.saveOrder(currentOrder);
        view.printOrderConfirmation(savedOrder);
    }

    private void addChosenDishToOrder(Order currentOrder, Set<Dish> allDishes) {
        OrderPosition orderPosition = askForDishSelectionAndCreateOrderPosition(allDishes);
        assignOrderAndOrderPosition(currentOrder, orderPosition);
        view.printOrderDetails(currentOrder);
    }

    private void assignOrderAndOrderPosition(Order currentOrder, OrderPosition orderPosition) {
        orderPosition.setOrder(currentOrder);
        orderService.updateOrdersPositions(currentOrder, orderPosition);
    }

    private OrderPosition askForDishSelectionAndCreateOrderPosition(Set<Dish> allDishes) {
        Dish chosenDish = view.selectDish(new ArrayList<>(allDishes));
        int amount = view.askForAmount();
        return getOrderPosition(chosenDish, amount);
    }

    private static OrderPosition getOrderPosition(Dish chosenDish, int amount) {
        OrderPosition orderPosition = new OrderPosition();
        orderPosition.setDish(chosenDish);
        orderPosition.setAmount(amount);
        return orderPosition;
    }
}
