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

}
