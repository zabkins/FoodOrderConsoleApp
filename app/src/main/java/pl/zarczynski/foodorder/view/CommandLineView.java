package pl.zarczynski.foodorder.view;

import org.springframework.stereotype.Component;
import pl.zarczynski.foodorder.domain.Dish;
import pl.zarczynski.foodorder.domain.Ingredient;
import pl.zarczynski.foodorder.domain.Order;
import pl.zarczynski.foodorder.domain.OrderPosition;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
@Component
public class CommandLineView implements View{
    private final InputReader inputReader;
    private final OutputWriter outputWriter;

    public CommandLineView(InputReader inputReader, OutputWriter outputWriter) {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
    }

    @Override
    public void welcomeClient() {
        outputWriter.println("Welcome to our restaurant! Take a look at our menu and feel free to order anything!");
    }

    @Override
    public void printDishes(List<Dish> dishes) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < dishes.size(); i++){
            Dish dish = dishes.get(i);
            sb.append(i + 1).append(". ").append(dish.getName()).append(" - ").append(dish.getPrice()).append("PLN\n");
            Set<Ingredient> dishIngredients = dish.getIngredients();
            sb.append("\t(");
            for (Ingredient dishIngredient : dishIngredients) {
                sb.append(dishIngredient.getName()).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(")\n");
        }
        outputWriter.println(sb.toString());
    }

    @Override
    public Dish selectDish(List<Dish> dishes) {
        while (true){
            outputWriter.print("Please enter the name of the dish you would like to add to or delete from the order: ");
            String userInput = inputReader.nextLine();
            for (Dish dish : dishes) {
                if(userInput.equals(dish.getName())){
                    return dish;
                }
            }
            outputWriter.println("No such dish available. Please try again");
        }
    }

    @Override
    public int askForAmount() {
        outputWriter.print("Please enter the amount you would like to order. This overwrites old value, entering zero removes the item from your order:");
        int pieces = -1;
        while (true){
            String userInput = inputReader.nextLine();
            try{
                pieces = Integer.parseInt(userInput);
            } catch (NumberFormatException e){
                outputWriter.print("Invalid input. Please enter 0 or more: ");
                continue;
            }
            if(pieces >= 0){
                return pieces;
            } else {
                outputWriter.print("Invalid input. Please enter 0 or more: ");
            }
        }
    }

    @Override
    public void printOrderConfirmation(Order order) {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
        String formattedDateTime = order.getCreationTimestamp().format(formatter);
        List<OrderPosition> orderPositions = order.getOrderPositions();
        sb.append("Your order has been made. Thank you for choosing us! Order details:\n");
        sb.append("ID: ").append(order.getId()).append(" | Date: ").append(formattedDateTime).append("\n");
        sb.append(getPrintListOfOrderPositions(orderPositions));
        sb.append("TOTAL PRICE: ").append(order.getTotalPrice()).append(" PLN.\n");
        outputWriter.println(sb.toString());
    }

    private static String getPrintListOfOrderPositions(List<OrderPosition> orderPositions) {
        StringBuilder sb = new StringBuilder();
        for (OrderPosition orderPosition : orderPositions) {
            Dish dish = orderPosition.getDish();
            sb.append("\t").append(dish.getName()).append(" - ").append(orderPosition.getAmount()).append(" piece(s)").append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean askToMakeAnotherOrder() {
        outputWriter.print("Would you like to place another order? (y/n) : ");
        return askForYesOrNo();
    }

    @Override
    public boolean promptForOrderChange() {
        outputWriter.print("Are you finished with your order? (y/n) : ");
        return askForYesOrNo();
    }

    private boolean askForYesOrNo() {
        while (true){
            String userInput = inputReader.nextLine();
            if("y".equals(userInput)){
                return true;
            } else if ("n".equals(userInput)){
                return false;
            }
            outputWriter.println("Invalid input. Please enter y or n");
        }
    }

    @Override
    public void printOrderDetails(Order currentOrder) {
        List<OrderPosition> orderPositions = currentOrder.getOrderPositions();
        StringBuilder sb = new StringBuilder();
        sb.append("Dish has been added to your order. Currently your order is :\n");
        sb.append(getPrintListOfOrderPositions(orderPositions));
        sb.append("Total price: ").append(currentOrder.getTotalPrice()).append(" PLN\n");
        outputWriter.println(sb.toString());
    }
}
