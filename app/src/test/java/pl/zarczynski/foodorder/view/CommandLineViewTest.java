package pl.zarczynski.foodorder.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pl.zarczynski.foodorder.domain.Dish;
import pl.zarczynski.foodorder.domain.Ingredient;
import pl.zarczynski.foodorder.domain.IngredientType;
import pl.zarczynski.foodorder.domain.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommandLineViewTest {
    private InputReader inputReader;
    private OutputWriter outputWriter;
    private View view;
    private List<Dish> dishList;

    @BeforeEach
    public void setUp(){
        inputReader = mock(InputReader.class);
        outputWriter = mock(OutputWriter.class);
        view = new CommandLineView(inputReader,outputWriter);
        dishList = generateDishList();
    }

    @Test
    public void testIfViewCallsOutputWriterWhenWelcomingUser(){
        view.welcomeClient();
        verify(outputWriter).println(anyString());
    }

    @Test
    public void testIfTryingToPrintEmptyDishListPrintsEmptyString(){
        view.printDishes(new ArrayList<>());
        verify(outputWriter).println("");
    }

    @Test
    public void testIfPopulatedDishListPrints(){
        view.printDishes(dishList);

        verify(outputWriter).println(anyString());
    }

    @Test
    public void testIfSelectingIncorrectDishPromptsForChoiceAgain(){
        when(inputReader.nextLine()).thenReturn("Dish3").thenReturn("Dish4").thenReturn("Dish1");
        view.selectDish(dishList);
        verify(outputWriter,atLeast(1)).println("No such dish available. Please try again");
    }

    @Test
    public void testIfViewReturnsCorrectDish(){
        when(inputReader.nextLine()).thenReturn("Dish1");
        assertEquals(dishList.get(0),view.selectDish(dishList));
    }

    @Test
    public void testIfViewReturnsCorrectAmount(){
        when(inputReader.nextLine()).thenReturn("2");
        assertEquals(2,view.askForAmount());
    }

    @Test
    public void testIifViewAsksForCorrectAmountWhenInputIsIncorrect(){
        when(inputReader.nextLine()).thenReturn("b").thenReturn("-2").thenReturn("4");
        view.askForAmount();
        verify(outputWriter,atLeast(1)).print("Invalid input. Please enter 0 or more: ");
    }

    @Test
    public void testIfViewCallsOutputWriterCorrectlyWhenPrintingOrderConfirmation(){
        Order order = new Order();
        order.setOrderPositions(new ArrayList<>());
        order.setCreationTimestamp(LocalDateTime.now());
        view.printOrderConfirmation(order);

        verify(outputWriter).println(anyString());
    }

    @Test
    public void testIfViewCallsOutputWriterCorrectlyWhenAskingForAnotherOrder(){
        when(inputReader.nextLine()).thenReturn("y");

        view.askToMakeAnotherOrder();

        verify(outputWriter).print(anyString());
    }

    @Test
    public void testIfViewCallsOutputWriterCorrectlyWhenAskingForOrderChange(){
        when(inputReader.nextLine()).thenReturn("y");

        view.promptForOrderChange();

        verify(outputWriter).print(anyString());
    }

    @Test
    public void testIfViewCallsOutputWriterCorrectlyWhenPrintingOrderDetails(){
        Order order = new Order();
        order.setOrderPositions(new ArrayList<>());

        view.printOrderDetails(order);

        verify(outputWriter).println(anyString());
    }

    private static List<Dish> generateDishList() {
        Ingredient ingredient = new Ingredient();
        ingredient.setName("INGREDIENT");
        ingredient.setIngredientType(IngredientType.OTHER);
        List<Dish> dishList = List.of(
                new Dish.DishBuilder().name("Dish1").price(1).addIngredient(ingredient).build(),
                new Dish.DishBuilder().name("Dish2").price(2).addIngredient(ingredient).build()
        );
        return dishList;
    }
}