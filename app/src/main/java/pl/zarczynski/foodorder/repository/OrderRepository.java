package pl.zarczynski.foodorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zarczynski.foodorder.domain.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
