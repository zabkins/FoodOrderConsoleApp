package pl.zarczynski.foodorder.data;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import pl.zarczynski.foodorder.domain.Dish;
import pl.zarczynski.foodorder.domain.Ingredient;
import pl.zarczynski.foodorder.domain.IngredientType;
import pl.zarczynski.foodorder.repository.DishRepository;
import pl.zarczynski.foodorder.repository.IngredientRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Transactional
public class DataLoader implements InitializingBean {
    private Map<String, Ingredient> ingredientMap;
    private final IngredientRepository ingredientRepository;
    private final DishRepository dishRepository;

    public DataLoader(IngredientRepository ingredientRepository, DishRepository dishRepository) {
        this.ingredientRepository = ingredientRepository;
        this.dishRepository = dishRepository;
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        createIngredients();
        createAvailableDishes();
    }

    private void createIngredients() {
        List<Ingredient> ingredients = List.of(
                new Ingredient("PASTA", IngredientType.GRAINS),
                new Ingredient("RICE", IngredientType.GRAINS),
                new Ingredient("BREAD", IngredientType.GRAINS),
                new Ingredient("DOUGH", IngredientType.GRAINS),
                new Ingredient("TORTILLA", IngredientType.GRAINS),
                new Ingredient("BUN", IngredientType.GRAINS),
                new Ingredient("BEEF", IngredientType.MEAT),
                new Ingredient("CHICKEN", IngredientType.MEAT),
                new Ingredient("PEPPERONI", IngredientType.MEAT),
                new Ingredient("POTATO", IngredientType.VEGETABLE),
                new Ingredient("TOMATO", IngredientType.VEGETABLE),
                new Ingredient("LETTUCE", IngredientType.VEGETABLE),
                new Ingredient("CUCUMBER", IngredientType.VEGETABLE),
                new Ingredient("ONION", IngredientType.VEGETABLE),
                new Ingredient("BROCCOLI", IngredientType.VEGETABLE),
                new Ingredient("ORANGE", IngredientType.FRUIT),
                new Ingredient("MANGO", IngredientType.FRUIT),
                new Ingredient("APPLE", IngredientType.FRUIT),
                new Ingredient("CHEDDAR", IngredientType.DAIRY),
                new Ingredient("MOZZARELLA", IngredientType.DAIRY),
                new Ingredient("KETCHUP", IngredientType.OTHER),
                new Ingredient("MAYONNAISE", IngredientType.OTHER),
                new Ingredient("GARLIC SAUCE", IngredientType.OTHER),
                new Ingredient("CURRY SAUCE", IngredientType.OTHER),
                new Ingredient("TOMATO SAUCE", IngredientType.OTHER),
                new Ingredient("BBQ SAUCE", IngredientType.OTHER)
        );
        ingredientRepository.saveAll(ingredients);
        ingredientMap = ingredientRepository.findAll().stream()
                .collect(Collectors.toMap(Ingredient::getName, ingredient -> ingredient));
    }

    private void createAvailableDishes() {
        List<Dish> dishes = List.of(
                new Dish.DishBuilder()
                        .name("Beef Burger")
                        .price(35)
                        .addIngredient(ingredientMap.get("BUN")).addIngredient(ingredientMap.get("BEEF")).addIngredient(ingredientMap.get("TOMATO"))
                        .addIngredient(ingredientMap.get("LETTUCE")).addIngredient(ingredientMap.get("ONION")).addIngredient(ingredientMap.get("CHEDDAR"))
                        .addIngredient(ingredientMap.get("BBQ SAUCE"))
                        .build(),
                new Dish.DishBuilder()
                        .name("Chicken Burger")
                        .price(32)
                        .addIngredient(ingredientMap.get("BUN")).addIngredient(ingredientMap.get("CHICKEN")).addIngredient(ingredientMap.get("TOMATO"))
                        .addIngredient(ingredientMap.get("LETTUCE")).addIngredient(ingredientMap.get("ONION")).addIngredient(ingredientMap.get("CHEDDAR"))
                        .addIngredient(ingredientMap.get("BBQ SAUCE"))
                        .build(),
                new Dish.DishBuilder()
                        .name("Spaghetti Bolognese")
                        .price(28)
                        .addIngredient(ingredientMap.get("PASTA")).addIngredient(ingredientMap.get("BEEF")).addIngredient(ingredientMap.get("TOMATO"))
                        .build(),
                new Dish.DishBuilder()
                        .name("Chicken Salad")
                        .price(24)
                        .addIngredient(ingredientMap.get("CHICKEN")).addIngredient(ingredientMap.get("LETTUCE")).addIngredient(ingredientMap.get("BREAD"))
                        .addIngredient(ingredientMap.get("TOMATO")).addIngredient(ingredientMap.get("CUCUMBER")).addIngredient(ingredientMap.get("BROCCOLI"))
                        .addIngredient(ingredientMap.get("MOZZARELLA")).addIngredient(ingredientMap.get("GARLIC SAUCE"))
                        .build(),
                new Dish.DishBuilder()
                        .name("Fries")
                        .price(11)
                        .addIngredient(ingredientMap.get("POTATO")).addIngredient(ingredientMap.get("KETCHUP")).addIngredient(ingredientMap.get("MAYONNAISE"))
                        .build(),
                new Dish.DishBuilder()
                        .name("Veggie Tortilla")
                        .price(20)
                        .addIngredient(ingredientMap.get("TORTILLA")).addIngredient(ingredientMap.get("TOMATO")).addIngredient(ingredientMap.get("CUCUMBER"))
                        .addIngredient(ingredientMap.get("ONION")).addIngredient(ingredientMap.get("LETTUCE")).addIngredient(ingredientMap.get("MOZZARELLA"))
                        .build(),
                new Dish.DishBuilder()
                        .name("Chicken Curry")
                        .price(38)
                        .addIngredient(ingredientMap.get("RICE")).addIngredient(ingredientMap.get("CHICKEN")).addIngredient(ingredientMap.get("CURRY SAUCE"))
                        .build(),
                new Dish.DishBuilder()
                        .name("Margherita")
                        .price(28)
                        .addIngredient(ingredientMap.get("DOUGH")).addIngredient(ingredientMap.get("TOMATO SAUCE")).addIngredient(ingredientMap.get("MOZZARELLA"))
                        .build(),
                new Dish.DishBuilder()
                        .name("Pepperoni Pizza")
                        .price(31)
                        .addIngredient(ingredientMap.get("DOUGH")).addIngredient(ingredientMap.get("TOMATO SAUCE")).addIngredient(ingredientMap.get("MOZZARELLA"))
                        .addIngredient(ingredientMap.get("PEPPERONI"))
                        .build(),
                new Dish.DishBuilder()
                        .name("BBQ Pizza")
                        .price(30)
                        .addIngredient(ingredientMap.get("DOUGH")).addIngredient(ingredientMap.get("BBQ SAUCE")).addIngredient(ingredientMap.get("MOZZARELLA"))
                        .addIngredient(ingredientMap.get("CHEDDAR")).addIngredient(ingredientMap.get("PEPPERONI")).addIngredient(ingredientMap.get("ONION"))
                        .build(),
                new Dish.DishBuilder()
                        .name("Kebab Pizza")
                        .price(30)
                        .addIngredient(ingredientMap.get("DOUGH")).addIngredient(ingredientMap.get("TOMATO SAUCE")).addIngredient(ingredientMap.get("CHICKEN"))
                        .addIngredient(ingredientMap.get("MOZZARELLA")).addIngredient(ingredientMap.get("ONION"))
                        .build(),
                new Dish.DishBuilder()
                        .name("Fruit Dessert")
                        .price(18)
                        .addIngredient(ingredientMap.get("ORANGE")).addIngredient(ingredientMap.get("MANGO")).addIngredient(ingredientMap.get("APPLE"))
                        .build()
        );
        dishRepository.saveAll(dishes);
    }
}
