package victor.training.java8.stream.menu;

import org.junit.Test;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Optional.of;
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
               800, listOf(DishPlay.menu.get(0)), //800 - [pork]
               700, listOf(DishPlay.menu.get(1)), //700 - [beef]
               400, listOf(DishPlay.menu.get(2)) //400 - [chicken]
            ),
            FISH, mapOf(
                300, listOf(DishPlay.menu.get(7)), //300 - [prawns]
                450, listOf(DishPlay.menu.get(8)) //450 - [salmon]
            ),
            OTHER, mapOf(
                530, listOf(DishPlay.menu.get(3)), //530 - [french fries]
                350, listOf(DishPlay.menu.get(4)), //350 - [rice]
                120, listOf(DishPlay.menu.get(5)), //120 - [season fruit]
                550, listOf(DishPlay.menu.get(6)) //550 - [pizza]
            )
        );

        Map<Dish.Type, Map<Object, List<Dish>>> result = dishPlay.groupDishesByTypeAndCaloricLevel();

        assertEquals(expectedDishesGrouping, result);
    }

    @Test
    public void countTypesOfDishes_listsExpectedTypesAndCounts() {
        Map<Dish.Type, Long> expectedTypeCounts = mapOf(
                MEAT, 3L,
                FISH, 2L,
                OTHER, 4L
        );

        Map<Dish.Type, Long> result = dishPlay.countTypesOfDishes();

        assertEquals(expectedTypeCounts, result);
    }

    @Test
    public void groupDishesToSetsOfDifferentType_listsExpectedDishesAsSetsByType() {
        Map<Dish.Type, Set<Dish>> expectedDishesAsSetsByType = mapOf(
            MEAT, setOf(
                DishPlay.menu.get(0), // pork
                DishPlay.menu.get(1), // beef
                DishPlay.menu.get(2)  // chicken
            ),
            FISH, setOf(
                DishPlay.menu.get(7), // prawns
                DishPlay.menu.get(8)  // salmon
            ),
            OTHER, setOf(
                DishPlay.menu.get(3), // french fries
                DishPlay.menu.get(4), // rice
                DishPlay.menu.get(5), // season fruit
                DishPlay.menu.get(6)  // pizza
            )
        );

        Map<Dish.Type, Set<Dish>> result = dishPlay.groupDishesToSetsOfDifferentType();

        assertEquals(expectedDishesAsSetsByType, result);
    }

    @Test
    public void countTotalCaloriesByType_givesMapOfTotalCaloriesOfDishesGroupedByType() {
        Map<Dish.Type, Long> expectedTotalCaloriesOfDishesByType = mapOf(
            MEAT, 1900L,
            FISH, 750L,
            OTHER, 1550L
        );

        Map<Dish.Type, Long> result = dishPlay.countTotalCaloriesByType();

        assertEquals(expectedTotalCaloriesOfDishesByType, result);
    }

    @Test
    public void listMostCaloricDishByEachType_givesMapOfDishTypeToHighestCalorieDishOfThatType() {
        Map<Dish.Type, Optional<Dish>> expectedMapOfDishTypeToHighestCalorieDishOfThatType = mapOf(
            MEAT, of(DishPlay.menu.get(0)), // pork, 800 calories
            FISH, of(DishPlay.menu.get(8)), // salmon, 450 calories
            OTHER, of(DishPlay.menu.get(6)) // pizza, 550 calories
        );

        Map<Dish.Type, Optional<Dish>> result = dishPlay.listMostCaloricDishByEachType();

        assertEquals(expectedMapOfDishTypeToHighestCalorieDishOfThatType, result);
    }

    private Set<Dish> setOf(Dish... dishes) {
        return new HashSet<>(listOf(dishes));
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
                key4, value4
        );
        map.put(key5, value5);
        return map;
    }

    private List<Dish> listOf(Dish... dishes) {
        return asList(dishes);
    }



}
