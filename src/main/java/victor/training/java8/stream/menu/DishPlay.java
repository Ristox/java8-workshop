package victor.training.java8.stream.menu;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class DishPlay {
   public static final List<Dish> menu = asList(
       new Dish("pork", false, 800, Dish.Type.MEAT),
       new Dish("beef", false, 700, Dish.Type.MEAT),
       new Dish("chicken", false, 400, Dish.Type.MEAT),
       new Dish("french fries", true, 530, Dish.Type.OTHER),
       new Dish("rice", true, 350, Dish.Type.OTHER),
       new Dish("season fruit", true, 120, Dish.Type.OTHER),
       new Dish("pizza", true, 550, Dish.Type.OTHER),
       new Dish("prawns", false, 300, Dish.Type.FISH),
       new Dish("salmon", false, 450, Dish.Type.FISH));

   public static void main(String[] args) {
      // The above code sample + problems are from Java 8 in Action book (Manning)
      DishPlay dishPlay = new DishPlay();

      System.out.println("Low calorie dishes: " + dishPlay.listOnlyLowCalorieDishes());

      System.out.println("Names of 3 highest calorie menu items: " + dishPlay.listThreeHighCalorieDishNames());

      // TODO find out the 2nd and 3rd most caloric items

      // TODO find vegetarian dishes

      // TODO find 2 dishes with meat

      // TODO Map<Type, Map<Object, List<Dish>>> dishesByTypeAndCaloricLevel

      // TODO Map<Dish.Type, Long> typesCount

      // TODO Map<Dish.Type, Set<Dish>> groupedAsSets

      // TODO Map<Dish.Type, Integer> totalCaloriesByType

      // TODO Map<Dish.Type, Optional<Dish>> mostCaloricByType
   }

   public List<Dish> listOnlyLowCalorieDishes() {
      return menu.stream().filter(Dish::hasLowCalories).collect(toList());
   }

   public List<String> listThreeHighCalorieDishNames() {
      return menu.stream()
              .sorted(comparing(Dish::getCalories))
              .map(Dish::getName)
              .limit(3)
              .collect(toList());
   }
}
