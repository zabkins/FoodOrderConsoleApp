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
    private Map<String, Ingredient> ingredientsMap;
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

    private void createAvailableDishes() {
        Dish beefBurger = new Dish.DishBuilder()
                .name("Beef Burger")
                .price(35)
                .addIngredient(ingredientsMap.get("BUN")).addIngredient(ingredientsMap.get("BEEF")).addIngredient(ingredientsMap.get("TOMATO"))
                .addIngredient(ingredientsMap.get("LETTUCE")).addIngredient(ingredientsMap.get("ONION")).addIngredient(ingredientsMap.get("CHEDDAR"))
                .addIngredient(ingredientsMap.get("BBQ SAUCE"))
                .build();
        dishRepository.save(beefBurger);
    }

    private void createIngredients() {
        List<Ingredient> ingredients = List.of(
                new Ingredient("PASTA",IngredientType.GRAINS),
                new Ingredient("RICE",IngredientType.GRAINS),
                new Ingredient("BREAD",IngredientType.GRAINS),
                new Ingredient("BUN",IngredientType.GRAINS),
                new Ingredient("BEEF",IngredientType.MEAT),
                new Ingredient("CHICKEN",IngredientType.MEAT),
                new Ingredient("POTATO",IngredientType.VEGETABLE),
                new Ingredient("TOMATO",IngredientType.VEGETABLE),
                new Ingredient("LETTUCE",IngredientType.VEGETABLE),
                new Ingredient("CUCUMBER",IngredientType.VEGETABLE),
                new Ingredient("ONION",IngredientType.VEGETABLE),
                new Ingredient("BROCCOLI",IngredientType.VEGETABLE),
                new Ingredient("ORANGE",IngredientType.FRUIT),
                new Ingredient("MANGO",IngredientType.FRUIT),
                new Ingredient("APPLE",IngredientType.FRUIT),
                new Ingredient("EGG",IngredientType.DAIRY),
                new Ingredient("CHEDDAR",IngredientType.DAIRY),
                new Ingredient("MOZZARELLA",IngredientType.DAIRY),
                new Ingredient("KETCHUP",IngredientType.OTHER),
                new Ingredient("MAYONNAISE",IngredientType.OTHER),
                new Ingredient("GARLIC SAUCE",IngredientType.OTHER),
                new Ingredient("CHEESE SAUCE",IngredientType.OTHER),
                new Ingredient("BBQ SAUCE",IngredientType.OTHER)
        );
        ingredientRepository.saveAll(ingredients);
        ingredientsMap = ingredientRepository.findAll().stream()
                .collect(Collectors.toMap(Ingredient::getName, ingredient -> ingredient));
    }
}
