package pl.zarczynski.foodorder.service;

import org.springframework.stereotype.Service;
import pl.zarczynski.foodorder.domain.Dish;
import pl.zarczynski.foodorder.repository.DishRepository;

import java.util.List;

@Service
public class DefaultDishService implements DishService {
    private final DishRepository dishRepository;

    public DefaultDishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Dish> getAllDishes() {
        return null;
    }
}
