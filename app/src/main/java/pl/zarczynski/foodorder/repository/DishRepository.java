package pl.zarczynski.foodorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zarczynski.foodorder.domain.Dish;
@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
}
