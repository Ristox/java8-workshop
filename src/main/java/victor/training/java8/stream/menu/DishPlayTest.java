package victor.training.java8.stream.menu;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static victor.training.java8.stream.menu.Dish.Type.*;

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

    @Test
    public void groupDishesByTypeAndCaloricLevel_listsExpectedGroupsOfDishes() {
        Map<Dish.Type, Map<Object, List<Dish>>> expectedDishesGrouping = mapOf(
            MEAT, mapOf(
               800, list(DishPlay.menu.get(0)), //800 - [pork]
               700, list(DishPlay.menu.get(1)), //700 - [beef]
               400, list(DishPlay.menu.get(2)) //400 - [chicken]
            ),
            FISH, mapOf(
                300, list(DishPlay.menu.get(7)), //300 - [prawns]
                400, list(DishPlay.menu.get(8)) //400 - [salmon]
            ),
            OTHER, mapOf(
                530, list(DishPlay.menu.get(3)), //530 - [french fries]
                350, list(DishPlay.menu.get(4)), //350 - [rice]
                350, list(DishPlay.menu.get(5)) //120 - [season fruit]
            )
        );

        Map<Dish.Type, Map<Object, List<Dish>>> result = dishPlay.groupDishesByTypeAndCaloricLevel();

        assertEquals(expectedDishesGrouping, result);
    }

    private <K, V> Map<K, V> mapOf(
        K key1, V value1,
        K key2, V value2
    ) {
        Map<K, V> map = new HashMap<>();
        map.put(key1, value1);
        map.put(key2, value2);
        return map;
    }

    private <K, V> Map<K, V> mapOf(
            K key1, V value1,
            K key2, V value2,
            K key3, V value3
    ) {
        Map<K, V> map = mapOf(
            key1, value1,
            key2, value2
        );
        map.put(key3, value3);
        return map;
    }

    private <K, V> Map<K, V> mapOf(
            K key1, V value1,
            K key2, V value2,
            K key3, V value3,
            K key4, V value4
    ) {
        Map<K, V> map = mapOf(
                key1, value1,
                key2, value2,
                key3, value3
        );
        map.put(key4, value4);
        return map;
    }

    private <K, V> Map<K, V> mapOf(
            K key1, V value1,
            K key2, V value2,
            K key3, V value3,
            K key4, V value4,
            K key5, V value5
    ) {
        Map<K, V> map = mapOf(
                key1, value1,
                key2, value2,
                key3, value3,
                key4, value4,
        );
        map.put(key5, value5);
        return map;
    }

    private List<Dish> list(Dish... dishes) {
        return asList(dishes);
    }



}
