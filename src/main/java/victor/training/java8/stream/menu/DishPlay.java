package victor.training.java8.stream.menu;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.text.MessageFormat.format;
import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;
import static victor.training.java8.stream.menu.Dish.Type.MEAT;

public class DishPlay {
   public static final List<Dish> menu = asList(
       new Dish("pork", false, 800, MEAT),
       new Dish("beef", false, 700, MEAT),
       new Dish("chicken", false, 400, MEAT),
       new Dish("french fries", true, 530, Dish.Type.OTHER),
       new Dish("rice", true, 350, Dish.Type.OTHER),
       new Dish("season fruit", true, 120, Dish.Type.OTHER),
       new Dish("pizza", true, 550, Dish.Type.OTHER),
       new Dish("prawns", false, 300, Dish.Type.FISH),
       new Dish("salmon", false, 450, Dish.Type.FISH));

   public static void main(String[] args) {
      // The above code sample + problems are from Java 8 in Action book (Manning)
      DishPlay dishPlay = new DishPlay();

      log("Low calorie dishes: {0}", dishPlay.listOnlyLowCalorieDishes());

      log("Names of 3 highest calorie dishes: {0}", dishPlay.listThreeHighCalorieDishNames());

      log("2nd and 3rd highest calorie dishes: {0}", dishPlay.listSecondAndThirdHighestCalorieDishes());

      log("Vegetarian dishes: {0}", dishPlay.listVegetarianDishes());

      log("First 2 dishes containing meat: {0}", dishPlay.listFirstTwoDishesContainingMeat());

      log("Dishes grouped first by type, then by their caloric level (2-level hierarchy): {0}",
           dishPlay.groupDishesByTypeAndCaloricLevel());

      log("Counts of different types of dishes: {0}", dishPlay.countTypesOfDishes());

      log("Dishes grouped to Sets under different Dish Types: {0}", dishPlay.groupDishesToSetsOfDifferentType());

      // TODO Map<Dish.Type, Integer> totalCaloriesByType

      // TODO Map<Dish.Type, Optional<Dish>> mostCaloricByType
   }

   private static void log(String s, Object... params) {
      System.out.println(format(s, params));
   }

   public List<Dish> listOnlyLowCalorieDishes() {
      return menu.stream().filter(Dish::hasLowCalories).collect(toList());
   }

   public List<String> listThreeHighCalorieDishNames() {
      return menu.stream()
              .sorted(comparing(Dish::getCalories).reversed())
              .map(Dish::getName)
              .limit(3)
              .collect(toList());
   }

   public List<Dish> listSecondAndThirdHighestCalorieDishes() {
      return menu.stream()
              .sorted(comparing(Dish::getCalories).reversed())
              .skip(1)
              .limit(2)
              .collect(toList());

   }

   public List<Dish> listVegetarianDishes() {
      return menu.stream()
              .filter(Dish::isVegetarian)
              .collect(toList());
   }

   public List<Dish> listFirstTwoDishesContainingMeat() {
      return menu.stream()
              .filter(Dish::containsMeat)
              .limit(2)
              .collect(toList());
   }

   public Map<Dish.Type, Map<Object, List<Dish>>> groupDishesByTypeAndCaloricLevel() {
      return menu.stream()
              .collect(
                  groupingBy(Dish::getType,
                       groupingBy(Dish::getCalories)
                  )
              );
   }

   public Map<Dish.Type, Long> countTypesOfDishes() {
      return menu.stream()
              .map(Dish::getType)
              .collect(groupingBy(identity(), counting()));
   }

   public Map<Dish.Type, Set<Dish>> groupDishesToSetsOfDifferentType() {
      return menu.stream()
              .collect(
                   groupingBy(Dish::getType, toSet())
              );
   }

   public Map<Dish.Type, Long> countTotalCaloriesByType() {
      return menu.stream()
              .collect(
                   groupingBy(Dish::getType, summingLong(Dish::getCalories))
              );
   }
}
