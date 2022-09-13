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
    @OneToMany(mappedBy = "order")
    private List<OrderPosition> orderPositions;

    public boolean addPosition(OrderPosition orderPosition){
        if(orderPositions == null){
            orderPositions = new ArrayList<>();
        }
        orderPositions.add(orderPosition);
        totalPrice += orderPosition.getDish().getPrice() * orderPosition.getAmount();
        return true;
    }

    public boolean removePosition(OrderPosition orderPosition) {
        return orderPositions.remove(orderPosition);
    }

    public void updatePrice(){
        this.totalPrice = 0;
        for (OrderPosition orderPosition : this.orderPositions) {
            totalPrice += orderPosition.getDish().getPrice() * orderPosition.getAmount();
        }
    }
}
