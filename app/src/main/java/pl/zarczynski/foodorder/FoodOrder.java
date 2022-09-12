package pl.zarczynski.foodorder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.zarczynski.foodorder.domain.Dish;
import pl.zarczynski.foodorder.service.DishService;
import pl.zarczynski.foodorder.service.OrderService;
import pl.zarczynski.foodorder.view.View;

import java.util.ArrayList;
import java.util.List;
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
        Set<Dish> allDishes = dishService.getAllDishesWithIngredients();
        view.printDishes(new ArrayList<>(allDishes));
    }
}
