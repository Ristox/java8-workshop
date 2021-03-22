package victor.training.java8.stream.car;

public class ElectricEngine extends Engine {

    public ElectricEngine() {

    }

    protected ElectricEngine(ElectricEngine another) {

    }

    @Override
    public ElectricEngine copy() {
        return new ElectricEngine(this);
    }

    @Override
    protected String ignite() {
        return "Blue energy activated";
    }
}
