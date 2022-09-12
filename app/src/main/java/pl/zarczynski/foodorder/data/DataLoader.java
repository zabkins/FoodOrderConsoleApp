package pl.zarczynski.foodorder.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.zarczynski.foodorder.domain.Dish;
import pl.zarczynski.foodorder.domain.Ingredient;
import pl.zarczynski.foodorder.domain.IngredientType;
import pl.zarczynski.foodorder.repository.DishRepository;
import pl.zarczynski.foodorder.repository.IngredientRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DataLoader implements CommandLineRunner {
    private Map<String, Ingredient> ingsMap;
    private final IngredientRepository ingredientRepository;
    private final DishRepository dishRepository;

    public DataLoader(IngredientRepository ingredientRepository, DishRepository dishRepository) {
        this.ingredientRepository = ingredientRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public void run(String... args) throws Exception {
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
        ingsMap = ingredientRepository.findAll().stream()
                .collect(Collectors.toMap(Ingredient::getName, ingredient -> ingredient));
    }

    private void createAvailableDishes() {
        List<Dish> dishes = List.of(
                new Dish.DishBuilder()
                        .name("Beef Burger")
                        .price(35)
                        .addIngredient(ingsMap.get("BUN")).addIngredient(ingsMap.get("BEEF")).addIngredient(ingsMap.get("TOMATO"))
                        .addIngredient(ingsMap.get("LETTUCE")).addIngredient(ingsMap.get("ONION")).addIngredient(ingsMap.get("CHEDDAR"))
                        .addIngredient(ingsMap.get("BBQ SAUCE"))
                        .build(),
                new Dish.DishBuilder()
                        .name("Chicken Burger")
                        .price(32)
                        .addIngredient(ingsMap.get("BUN")).addIngredient(ingsMap.get("CHICKEN")).addIngredient(ingsMap.get("TOMATO"))
                        .addIngredient(ingsMap.get("LETTUCE")).addIngredient(ingsMap.get("ONION")).addIngredient(ingsMap.get("CHEDDAR"))
                        .addIngredient(ingsMap.get("BBQ SAUCE"))
                        .build(),
                new Dish.DishBuilder()
                        .name("Spaghetti Bolognese")
                        .price(28)
                        .addIngredient(ingsMap.get("PASTA")).addIngredient(ingsMap.get("BEEF")).addIngredient(ingsMap.get("TOMATO"))
                        .build(),
                new Dish.DishBuilder()
                        .name("Chicken Salad")
                        .price(24)
                        .addIngredient(ingsMap.get("CHICKEN")).addIngredient(ingsMap.get("LETTUCE")).addIngredient(ingsMap.get("BREAD"))
                        .addIngredient(ingsMap.get("TOMATO")).addIngredient(ingsMap.get("CUCUMBER")).addIngredient(ingsMap.get("BROCCOLI"))
                        .addIngredient(ingsMap.get("MOZZARELLA")).addIngredient(ingsMap.get("GARLIC SAUCE"))
                        .build(),
                new Dish.DishBuilder()
                        .name("Fries")
                        .price(11)
                        .addIngredient(ingsMap.get("POTATO")).addIngredient(ingsMap.get("KETCHUP")).addIngredient(ingsMap.get("MAYONNAISE"))
                        .build(),
                new Dish.DishBuilder()
                        .name("Veggie Tortilla")
                        .price(20)
                        .addIngredient(ingsMap.get("TORTILLA")).addIngredient(ingsMap.get("TOMATO")).addIngredient(ingsMap.get("CUCUMBER"))
                        .addIngredient(ingsMap.get("ONION")).addIngredient(ingsMap.get("LETTUCE")).addIngredient(ingsMap.get("MOZZARELLA"))
                        .build(),
                new Dish.DishBuilder()
                        .name("Chicken Curry")
                        .price(38)
                        .addIngredient(ingsMap.get("RICE")).addIngredient(ingsMap.get("CHICKEN")).addIngredient(ingsMap.get("CURRY SAUCE"))
                        .build(),
                new Dish.DishBuilder()
                        .name("Margherita")
                        .price(28)
                        .addIngredient(ingsMap.get("DOUGH")).addIngredient(ingsMap.get("TOMATO SAUCE")).addIngredient(ingsMap.get("MOZZARELLA"))
                        .build(),
                new Dish.DishBuilder()
                        .name("Pepperoni Pizza")
                        .price(31)
                        .addIngredient(ingsMap.get("DOUGH")).addIngredient(ingsMap.get("TOMATO SAUCE")).addIngredient(ingsMap.get("MOZZARELLA"))
                        .addIngredient(ingsMap.get("PEPPERONI"))
                        .build(),
                new Dish.DishBuilder()
                        .name("BBQ Pizza")
                        .price(30)
                        .addIngredient(ingsMap.get("DOUGH")).addIngredient(ingsMap.get("BBQ SAUCE")).addIngredient(ingsMap.get("MOZZARELLA"))
                        .addIngredient(ingsMap.get("CHEDDAR")).addIngredient(ingsMap.get("PEPPERONI")).addIngredient(ingsMap.get("ONION"))
                        .build(),
                new Dish.DishBuilder()
                        .name("Kebab Pizza")
                        .price(30)
                        .addIngredient(ingsMap.get("DOUGH")).addIngredient(ingsMap.get("CHICKEN")).addIngredient(ingsMap.get("MOZZARELLA"))
                        .addIngredient(ingsMap.get("ONION"))
                        .build(),
                new Dish.DishBuilder()
                        .name("Fruit dessert")
                        .price(18)
                        .addIngredient(ingsMap.get("ORANGE")).addIngredient(ingsMap.get("MANGO")).addIngredient(ingsMap.get("APPLE"))
                        .build()
        );
        dishRepository.saveAll(dishes);
    }
}
