package victor.training.java8.stream.menu;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static java.text.MessageFormat.format;
import static victor.training.java8.stream.menu.Dish.Type.MEAT;

@RequiredArgsConstructor
public class Dish {

    public enum Type { MEAT, FISH, OTHER;}

    @Getter private final String name;

    @Getter private final boolean vegetarian;
    @Getter private final int calories;
    @Getter private final Type type;

    boolean hasLowCalories() {
        return getCalories() < 400;
    }

    boolean containsMeat() {
        return MEAT.equals(getType());
    }

    @Override
    public String toString() {
        return format("{0}=({1}, {2} calories)",
                       getClass().getSimpleName(), getName(), getCalories());
    }

}
