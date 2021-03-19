package victor.training.java8.stream.menu;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class DishPlayTest {

    private DishPlay dishPlay = new DishPlay();

    @Test
    public void listOnlyLowCalorieDishes_listsExpectedItemsWithLessThan400Calories() {
        List<Dish> expectedMenuItemsWithLowCalories = asList(
                DishPlay.menu.get(4), //rice 350 cal
                DishPlay.menu.get(5), //season fruit 120 cal
                DishPlay.menu.get(7)  //prawns 300 cal
        );

        List<Dish> result = dishPlay.listOnlyLowCalorieDishes();

        assertEquals(expectedMenuItemsWithLowCalories, result);
    }

    @Test
    public void listThreeHighCalorieDishNames_listsExpectedItemNamesWithHighestCalories() {
        List<String> expectedNamesOfMenuItemsWithHighestCalories = asList(
          "pork", "beef", "pizza"
        );

        List<String> result = dishPlay.listThreeHighCalorieDishNames();

        assertEquals(expectedNamesOfMenuItemsWithHighestCalories, result);
    }

    @Test
    public void listSecondAndThirdHighestCalorieDishes_listsExpectedItemNamesWithHighestCalories() {
        List<Dish> expectedSecondAndThirdHighestCalorieMenuItems = asList(
          DishPlay.menu.get(1), // beef, 700 calories
          DishPlay.menu.get(6) // pizza, 550 calories
        );

        List<Dish> result = dishPlay.listSecondAndThirdHighestCalorieDishes();

        assertEquals(expectedSecondAndThirdHighestCalorieMenuItems, result);
    }

    @Test
    public void listVegetarianDishes_listsExpectedDishesWhichAreVegetarian() {
        List<Dish> expectedVegetarianDishes = asList(
            DishPlay.menu.get(3), // french fries
            DishPlay.menu.get(4), // rice
            DishPlay.menu.get(5), // season fruit
            DishPlay.menu.get(6) // vegetarian pizza
        );

        List<Dish> result = dishPlay.listVegetarianDishes();

        assertEquals(expectedVegetarianDishes, result);
    }

    @Test
    public void listFirstTwoDishesContainingMeat_listsExpectedDishesWithTypeMeat() {
        List<Dish> expectedDishesContainingMeat = asList(
            DishPlay.menu.get(0), // pork
            DishPlay.menu.get(1)  // beef
        );

        List<Dish> result = dishPlay.listFirstTwoDishesContainingMeat();

        assertEquals(expectedDishesContainingMeat, result);
    }

}
