package pl.zarczynski.foodorder.view;

import pl.zarczynski.foodorder.domain.Dish;
import pl.zarczynski.foodorder.domain.Ingredient;

import java.util.List;

public class CommandLineView implements View{
    @Override
    public void welcomeClient() {
        System.out.println("Welcome to our restaurant! Take a look at our menu and feel free to order anything!");
    }

    @Override
    public void printDishes(List<Dish> dishes) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < dishes.size(); i++){
            Dish dish = dishes.get(i);
            sb.append(i).append(". ").append(dish.getName()).append(" - ").append(dish.getPrice()).append("$\n");
            List<Ingredient> dishIngredients = dish.getIngredients();
            sb.append("\t(");
            for (Ingredient dishIngredient : dishIngredients) {
                sb.append(dishIngredient.getName()).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(")\n");
        }
        System.out.println(sb.toString());
    }
}
