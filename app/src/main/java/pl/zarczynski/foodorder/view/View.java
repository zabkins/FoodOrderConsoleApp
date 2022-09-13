package pl.zarczynski.foodorder.view;

import pl.zarczynski.foodorder.domain.Dish;
import pl.zarczynski.foodorder.domain.Order;

import java.util.List;

public interface View {
    void welcomeClient();
    void printDishes(List<Dish> dishes);
    Dish selectDish(List<Dish> dishes);

    boolean promptForOrderChange();
    void printOrderDetails(Order currentOrder);

    int askForAmount();

    void printOrderConfirmation(Order order);

    boolean askToMakeAnotherOrder();
}