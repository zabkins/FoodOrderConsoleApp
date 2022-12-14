package pl.zarczynski.foodorder.service;

import org.springframework.stereotype.Service;
import pl.zarczynski.foodorder.domain.Dish;
import pl.zarczynski.foodorder.repository.DishRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class DefaultDishService implements DishService {
    private final DishRepository dishRepository;

    public DefaultDishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public Set<Dish> getAllDishesWithIngredients() {
        return dishRepository.findAllWithIngredients();
    }
}
