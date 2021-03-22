package victor.training.java8.stream.car;

public class Engine {

    public Engine() {

    }

    public Engine(Engine another) {

    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + hashCode();
    }
}
