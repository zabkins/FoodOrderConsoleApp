package pl.zarczynski.foodorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.zarczynski.foodorder.domain.Dish;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    @Override
    @Query("SELECT d FROM Dish d LEFT JOIN FETCH d.ingredients")
    List<Dish> findAll();
}
