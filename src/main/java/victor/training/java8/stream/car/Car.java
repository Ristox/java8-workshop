package victor.training.java8.stream.car;

public class Car {

    private final int year;
    private final Engine engine;

    public Car(int year, Engine engine) {
        this.year = year;
        this.engine = engine;
    }

    public Car(Car anotherCar) {
        year = anotherCar.year;

        engine = anotherCar.engine.copy();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + year + ":" + hashCode()
                + " (" + engine + ")";
    }
}
