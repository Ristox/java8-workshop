package victor.training.java8.stream.car;

public class CarRunner {

    public static void main(String[] args) {
        Car car1 = new Car(2015, new Engine());
        System.out.println(car1);

        Car car2 = new Car(car1);
        System.out.println(car2);

        System.out.println("");

        System.out.println("Starting car: " + car2.start());

        System.out.println("Starting 3rd car: " + new Car(2021, new ElectricEngine()).start());

    }

}
