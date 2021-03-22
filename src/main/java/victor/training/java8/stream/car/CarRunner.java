package victor.training.java8.stream.car;

public class CarRunner {

    public static void main(String[] args) {
        Car car1 = new Car(2015, new ElectricEngine());
        System.out.println(car1);

        Car car2 = new Car(car1);
        System.out.println(car2);
    }

}
