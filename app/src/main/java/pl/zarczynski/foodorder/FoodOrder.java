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
        Order currentOrder = new Order();
        view.welcomeClient();
        Set<Dish> allDishes = dishService.getAllDishesWithIngredients();
        view.printDishes(new ArrayList<>(allDishes));
        askForDishSelection(currentOrder, allDishes);
        while (!view.promptForOrderChange()) {
            askForDishSelection(currentOrder, allDishes);
        }
    }

    private void askForDishSelection(Order currentOrder, Set<Dish> allDishes) {
        Dish chosenDish = view.selectDish(new ArrayList<>(allDishes));
        int amount = view.askForAmount();
        OrderPosition orderPosition = getOrderPosition(chosenDish, amount);
        orderService.updateOrdersPositions(currentOrder,orderPosition);
        view.printOrderDetails(currentOrder);
    }

    private static OrderPosition getOrderPosition(Dish chosenDish, int amount) {
        OrderPosition orderPosition = new OrderPosition();
        orderPosition.setDish(chosenDish);
        orderPosition.setAmount(amount);
        return orderPosition;
    }
}
