package pl.zarczynski.foodorder.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "DISH")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    @ManyToMany
    private List<Ingredient> ingredients;

    private Dish(DishBuilder dishBuilder){
        this.name = dishBuilder.name;
        this.price = dishBuilder.price;
        this.ingredients = dishBuilder.ingredients;
    }

    public static class DishBuilder{
        private String name;
        private int price;
        private List<Ingredient> ingredients;

        public DishBuilder name(String name){
            this.name = name;
            return this;
        }

        public DishBuilder price(int price){
            this.price = price;
            return this;
        }

        public DishBuilder addIngredient(Ingredient ingredient){
            if(this.ingredients == null){
                ingredients = new ArrayList<>();
            }
            ingredients.add(ingredient);
            return this;
        }

        public Dish build(){
            return new Dish(this);
        }
    }
}
