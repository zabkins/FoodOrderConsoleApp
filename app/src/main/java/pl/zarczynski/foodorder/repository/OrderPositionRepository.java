package pl.zarczynski.foodorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zarczynski.foodorder.domain.OrderPosition;
@Repository
public interface OrderPositionRepository extends JpaRepository<OrderPosition, Long> {
}
