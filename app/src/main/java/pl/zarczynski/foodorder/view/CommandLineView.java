package pl.zarczynski.foodorder.view;

import org.springframework.stereotype.Component;
import pl.zarczynski.foodorder.domain.Dish;
import pl.zarczynski.foodorder.domain.Ingredient;
import pl.zarczynski.foodorder.domain.Order;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
@Component
public class CommandLineView implements View{
    private final Scanner consoleScanner = new Scanner(System.in);
    @Override
    public void welcomeClient() {
        System.out.println("Welcome to our restaurant! Take a look at our menu and feel free to order anything!");
    }

    @Override
    public void printDishes(List<Dish> dishes) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < dishes.size(); i++){
            Dish dish = dishes.get(i);
            sb.append(i).append(". ").append(dish.getName()).append(" - ").append(dish.getPrice()).append("PLN\n");
            Set<Ingredient> dishIngredients = dish.getIngredients();
            sb.append("\t(");
            for (Ingredient dishIngredient : dishIngredients) {
                sb.append(dishIngredient.getName()).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(")\n");
        }
        System.out.println(sb.toString());
    }

    @Override
    public Dish selectDish(List<Dish> dishes) {
        while (true){
            System.out.print("Please enter the name of the dish you would like to order: ");
            String userInput = consoleScanner.nextLine();
            for (Dish dish : dishes) {
                if(userInput.equals(dish.getName())){
                    return dish;
                }
            }
            System.out.println("No such dish available. Please try again");
        }
    }

    @Override
    public boolean promptForOrderChange() {
        System.out.println("Would you like to add something more to your order? (y/n).");
        while (true){
            String userInput = consoleScanner.nextLine();
            if("y".equals(userInput)){
                return true;
            } else if ("n".equals(userInput)){
                return false;
            }
            System.out.println("Invalid input. Please enter y or n");
        }
    }

    @Override
    public void printOrderDetails(Order currentOrder) {
        List<Dish> currentOrderDishes = currentOrder.getDishes();
        StringBuilder sb = new StringBuilder();
        sb.append("Dish has been added to your order. Currently your order is :\n");
        for (Dish currentOrderDish : currentOrderDishes) {
            sb.append("\t").append(currentOrderDish.getName()).append("\n");
        }
        sb.append("Total price: ").append(currentOrder.getTotalPrice()).append(" PLN\n");
        System.out.println(sb);
    }
}
