package victor.training.java8.stream.car;

public class TurboEngine extends Engine {

    public TurboEngine() {
        super();
    }

    public TurboEngine(TurboEngine another) {
        super(another);
    }

    @Override
    public Engine copy() {
        return new TurboEngine(this);
    }

    @Override
    protected String ignite() {
        return "Turbo ignition!";
    }
}
