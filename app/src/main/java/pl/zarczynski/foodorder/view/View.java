package pl.zarczynski.foodorder.view;

import pl.zarczynski.foodorder.domain.Dish;

import java.util.List;
import java.util.Set;

public interface View {
    void welcomeClient();
    void printDishes(List<Dish> dishes);
}