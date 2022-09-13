package pl.zarczynski.foodorder.service;

import pl.zarczynski.foodorder.domain.Dish;

import java.util.List;
import java.util.Set;

public interface DishService {
    Set<Dish> getAllDishesWithIngredients();
}
