package pl.zarczynski.foodorder.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "COMPLETE_ORDER")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int totalPrice;
    @CreationTimestamp
    @Column(name = "PLACED_AT")
    private LocalDateTime creationTimestamp;
    @ManyToMany
    private List<Dish> dishes;

    public boolean addDish(Dish dish){
        if(dishes == null){
            dishes = new ArrayList<>();
        }
        return dishes.add(dish);
    }
}
