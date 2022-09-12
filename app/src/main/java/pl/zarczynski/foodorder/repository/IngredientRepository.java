package pl.zarczynski.foodorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zarczynski.foodorder.domain.Ingredient;
@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
