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

        // FIXME: BAD instance checking. Type coupling, VERY "open for modification".
        if (anotherCar.engine instanceof TurboEngine) {
            this.engine = new TurboEngine((TurboEngine) anotherCar.engine);
        } else {
            engine = new Engine(anotherCar.engine);
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + year + ":" + hashCode()
                + " (" + engine + ")";
    }
}
