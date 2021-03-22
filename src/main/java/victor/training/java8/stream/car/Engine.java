package victor.training.java8.stream.car;

public class Engine {

    public Engine() {

    }

    protected Engine(Engine another) {

    }

    public Engine copy() {
        return new Engine(this);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + hashCode();
    }
}
